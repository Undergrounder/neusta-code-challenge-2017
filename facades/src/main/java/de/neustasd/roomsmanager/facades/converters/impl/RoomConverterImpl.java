package de.neustasd.roomsmanager.facades.converters.impl;

import de.neustasd.roomsmanager.core.entities.Person;
import de.neustasd.roomsmanager.core.entities.Room;
import de.neustasd.roomsmanager.core.services.converters.ConverterUtils;
import de.neustasd.roomsmanager.facades.converters.PersonConverter;
import de.neustasd.roomsmanager.facades.converters.RoomConverter;
import de.neustasd.roomsmanager.facades.dto.PersonDto;
import de.neustasd.roomsmanager.facades.dto.RoomDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** Created by Adrian Tello on 05/12/2017. */
@Component
public class RoomConverterImpl implements RoomConverter {

  private final PersonConverter personConverter;

  @Autowired
  public RoomConverterImpl(final PersonConverter personConverter) {
    this.personConverter = personConverter;
  }

  @Override
  public RoomDto convert(final Room source) throws ConversionException {
    final String roomNumber = source.getNumber();
    final List<Person> people = source.getPeople();
    final List<PersonDto> convertedPeople = ConverterUtils.convertAll(people, personConverter);

    return RoomDto.builder().room(roomNumber).people(convertedPeople).build();
  }
}
