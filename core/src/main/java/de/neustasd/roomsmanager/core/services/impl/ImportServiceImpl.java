package de.neustasd.roomsmanager.core.services.impl;

import de.neustasd.roomsmanager.core.entities.Person;
import de.neustasd.roomsmanager.core.entities.Room;
import de.neustasd.roomsmanager.core.entities.repositories.PersonRepository;
import de.neustasd.roomsmanager.core.entities.repositories.RoomRepository;
import de.neustasd.roomsmanager.core.services.ImportService;
import de.neustasd.roomsmanager.core.services.converters.Converter;
import de.neustasd.roomsmanager.core.services.converters.PersonDataConverter;
import de.neustasd.roomsmanager.core.services.converters.RoomDataConverter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Created by Adrian Tello on 01/12/2017. */
@Service
public class ImportServiceImpl implements ImportService {

  @Autowired private Validator validator;

  @Autowired private RoomRepository roomRepository;

  @Autowired private PersonRepository personRepository;

  @Autowired private RoomDataConverter roomDataConverter;

  @Autowired private PersonDataConverter personDataConverter;

  @Override
  @Transactional
  public synchronized ImportResultData importData(final ImportData importData)
      throws ImportException {
    validateImportData(importData);
    try {
      return doImportData(importData);
    } catch (Converter.ConversionException e) {
      throw new ImportException(e);
    }
  }

  private void validateImportData(final ImportData importData) throws InvalidImportDataException {
    final Set<ConstraintViolation<ImportData>> constraintViolationSet =
        validator.validate(importData);
    if (!constraintViolationSet.isEmpty()) {
      throw new InvalidImportDataException(constraintViolationSet);
    }
  }

  private ImportResultData doImportData(final ImportData importData)
      throws Converter.ConversionException {
    // Remove current data
    personRepository.deleteAllInBatch();
    roomRepository.deleteAllInBatch();

    // Convert import data
    final List<Room> roomList = new ArrayList<>();
    final List<Person> personList = new ArrayList<>();

    for (RoomData roomData : importData.getRoomDataList()) {
      final Room room = roomDataConverter.convert(roomData);
      roomList.add(room);

      for (PersonData personData : roomData.getPersonDataList()) {
        final Person person = personDataConverter.convert(personData);
        person.setRoom(room);

        personList.add(person);
      }
    }

    // Insert new items
    roomRepository.save(roomList);
    personRepository.save(personList);

    return ImportResultData.builder().rooms(roomList.size()).persons(personList.size()).build();
  }
}
