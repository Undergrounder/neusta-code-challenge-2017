package de.neusta_sd.roomsmanager.facades.impl;

import de.neusta_sd.roomsmanager.core.entities.NameAddition;
import de.neusta_sd.roomsmanager.core.entities.Person;
import de.neusta_sd.roomsmanager.core.entities.Room;
import de.neusta_sd.roomsmanager.core.entities.Title;
import de.neusta_sd.roomsmanager.core.services.RoomsService;
import de.neusta_sd.roomsmanager.facades.RoomsFacade;
import de.neusta_sd.roomsmanager.facades.dto.PersonDto;
import de.neusta_sd.roomsmanager.facades.dto.RoomDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Adrian Tello on 05/12/2017.
 */
@Component
public class RoomsFacadeImpl implements RoomsFacade {

    private final RoomsService roomsService;

    @Autowired
    public RoomsFacadeImpl(final RoomsService roomsService) {
        this.roomsService = roomsService;
    }

    @Override
    public Optional<RoomDto> findRoomByNumber(final String number) {
        Optional<RoomDto> roomDtoOptional = Optional.empty();

        final Optional<Room> roomOptional = roomsService.findRoomByNumber(number);
        if (roomOptional.isPresent()) {
            final Room room = roomOptional.get();
            final RoomDto roomDto = convertRoom(room);
            roomDtoOptional = Optional.of(roomDto);
        }

        return roomDtoOptional;
    }

    private RoomDto convertRoom(final Room room) {
        final String roomNumber = room.getNumber();
        final List<Person> people = room.getPeople();

        List<PersonDto> convertedPeople = convertPeople(people);
        return new RoomDto(roomNumber, convertedPeople);
    }

    private List<PersonDto> convertPeople(final List<Person> personList) {
        final List<PersonDto> convertedList = new ArrayList<>(personList.size());
        for (Person person : personList) {
            final PersonDto personDto = convertPerson(person);
            convertedList.add(personDto);
        }

        return convertedList;
    }

    private PersonDto convertPerson(final Person person) {
        final String firstName = person.getFirstName();
        final String lastName = person.getLastName();
        final Title title = person.getTitle();
        final String titleStr = (title == null) ? null : title.getName();
        final NameAddition nameAddition = person.getNameAddition();
        final String nameAdditionStr = (nameAddition == null) ? null : nameAddition.getName();
        final String ldapuser = person.getLdapUser();

        return new PersonDto(firstName, lastName, titleStr, nameAdditionStr, ldapuser);
    }
}
