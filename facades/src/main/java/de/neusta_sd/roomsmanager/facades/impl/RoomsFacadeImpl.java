package de.neusta_sd.roomsmanager.facades.impl;

import de.neusta_sd.roomsmanager.core.entities.Room;
import de.neusta_sd.roomsmanager.core.services.RoomsService;
import de.neusta_sd.roomsmanager.facades.RoomsFacade;
import de.neusta_sd.roomsmanager.facades.converters.RoomConverter;
import de.neusta_sd.roomsmanager.facades.dto.RoomDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by Adrian Tello on 05/12/2017.
 */
@Component
public class RoomsFacadeImpl implements RoomsFacade {

    private final RoomsService roomsService;

    private final RoomConverter roomConverter;

    @Autowired
    public RoomsFacadeImpl(final RoomsService roomsService, final RoomConverter roomConverter) {
        this.roomsService = roomsService;
        this.roomConverter = roomConverter;
    }

    @Override
    public Optional<RoomDto> findRoomByNumber(final String number) {
        Optional<RoomDto> roomDtoOptional = Optional.empty();

        final Optional<Room> roomOptional = roomsService.findRoomByNumber(number);
        if (roomOptional.isPresent()) {
            final Room room = roomOptional.get();
            final RoomDto roomDto = roomConverter.convert(room);
            roomDtoOptional = Optional.of(roomDto);
        }

        return roomDtoOptional;
    }
}
