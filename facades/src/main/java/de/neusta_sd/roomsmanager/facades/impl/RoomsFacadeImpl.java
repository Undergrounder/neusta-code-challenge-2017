package de.neusta_sd.roomsmanager.facades.impl;

import de.neusta_sd.roomsmanager.core.entities.Room;
import de.neusta_sd.roomsmanager.core.services.RoomsService;
import de.neusta_sd.roomsmanager.facades.RoomsFacade;
import de.neusta_sd.roomsmanager.facades.converters.RoomConverter;
import de.neusta_sd.roomsmanager.facades.converters.RoomsConverter;
import de.neusta_sd.roomsmanager.facades.dto.RoomDto;
import de.neusta_sd.roomsmanager.facades.dto.RoomsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Created by Adrian Tello on 05/12/2017.
 */
@Component
public class RoomsFacadeImpl implements RoomsFacade {

    private final RoomsService roomsService;

    private final RoomConverter roomConverter;

    private final RoomsConverter roomsConverter;

    public RoomsFacadeImpl(RoomsService roomsService, RoomConverter roomConverter, RoomsConverter roomsConverter) {
        this.roomsService = roomsService;
        this.roomConverter = roomConverter;
        this.roomsConverter = roomsConverter;
    }

    @Override
    public Optional<RoomDto> findRoomByNumber(final String number) {
        Optional<RoomDto> roomDtoOptional = Optional.empty();

        final Optional<Room> roomOptional = getRoomsService().findRoomByNumber(number);
        if (roomOptional.isPresent()) {
            final Room room = roomOptional.get();
            final RoomDto roomDto = getRoomConverter().convert(room);
            roomDtoOptional = Optional.of(roomDto);
        }

        return roomDtoOptional;
    }

    @Override
    public RoomsDto findRooms() {
        final List<Room> rooms = getRoomsService().findAll();
        return getRoomsConverter().convert(rooms);
    }

    private RoomConverter getRoomConverter() {
        return roomConverter;
    }

    private RoomsService getRoomsService() {
        return roomsService;
    }

    private RoomsConverter getRoomsConverter() {
        return roomsConverter;
    }
}
