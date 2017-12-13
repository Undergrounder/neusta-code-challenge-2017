package de.neusta_sd.roomsmanager.facades;

import de.neusta_sd.roomsmanager.facades.dto.RoomDto;
import de.neusta_sd.roomsmanager.facades.dto.RoomsDto;

import java.util.Optional;

/**
 * Created by Adrian Tello on 05/12/2017.
 */
public interface RoomsFacade {
    Optional<RoomDto> findRoomByNumber(final String number) throws InvalidRoomNumberException;

    RoomsDto findRooms();

    class InvalidRoomNumberException extends Exception{
        public InvalidRoomNumberException(String message) {
            super(message);
        }
    }
}
