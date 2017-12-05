package de.neusta_sd.roomsmanager.facades.converters.impl;

import de.neusta_sd.roomsmanager.core.entities.Person;
import de.neusta_sd.roomsmanager.core.entities.Room;
import de.neusta_sd.roomsmanager.core.services.converters.ConverterUtils;
import de.neusta_sd.roomsmanager.facades.converters.PersonConverter;
import de.neusta_sd.roomsmanager.facades.converters.RoomConverter;
import de.neusta_sd.roomsmanager.facades.dto.PersonDto;
import de.neusta_sd.roomsmanager.facades.dto.RoomDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Adrian Tello on 05/12/2017.
 */
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
        return new RoomDto(roomNumber, convertedPeople);
    }
}
