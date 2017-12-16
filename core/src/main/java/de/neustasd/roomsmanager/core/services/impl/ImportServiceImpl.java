package de.neustasd.roomsmanager.core.services.impl;

import de.neustasd.roomsmanager.core.entities.Person;
import de.neustasd.roomsmanager.core.entities.Room;
import de.neustasd.roomsmanager.core.entities.repositories.PersonRepository;
import de.neustasd.roomsmanager.core.entities.repositories.RoomRepository;
import de.neustasd.roomsmanager.core.services.ImportService;
import de.neustasd.roomsmanager.core.services.converters.Converter;
import de.neustasd.roomsmanager.core.services.converters.PersonDataConverter;
import de.neustasd.roomsmanager.core.services.converters.RoomDataConverter;
import de.neustasd.roomsmanager.core.services.data.ImportData;
import de.neustasd.roomsmanager.core.services.data.ImportResultData;
import de.neustasd.roomsmanager.core.services.data.PersonData;
import de.neustasd.roomsmanager.core.services.data.RoomData;
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

  private final Validator validator;

  private final RoomRepository roomRepository;

  private final PersonRepository personRepository;

  private final RoomDataConverter roomDataConverter;

  private final PersonDataConverter personDataConverter;

  /**
   * Constructor.
   *
   * @param validator Bean validator
   * @param roomRepository Room repository
   * @param personRepository Person repository
   * @param roomDataConverter RoomData converter
   * @param personDataConverter PersonData converter
   */
  @Autowired
  public ImportServiceImpl(
      Validator validator,
      RoomRepository roomRepository,
      PersonRepository personRepository,
      RoomDataConverter roomDataConverter,
      PersonDataConverter personDataConverter) {
    this.validator = validator;
    this.roomRepository = roomRepository;
    this.personRepository = personRepository;
    this.roomDataConverter = roomDataConverter;
    this.personDataConverter = personDataConverter;
  }

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
