package de.neusta_sd.roomsmanager.frontend.controllers;

import de.neusta_sd.roomsmanager.facades.RoomsFacade;
import de.neusta_sd.roomsmanager.facades.dto.RoomDto;
import de.neusta_sd.roomsmanager.facades.dto.RoomsDto;
import de.neusta_sd.roomsmanager.frontend.controllers.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static de.neusta_sd.roomsmanager.frontend.controllers.RoomsApiController.BASE_PATH;

/**
 * Created by Adrian Tello on 05/12/2017.
 */
@RestController
@RequestMapping(BASE_PATH)
public class RoomsApiController extends AbstractApiController {

    public static final String BASE_PATH = ApiController.BASE_PATH + "/room";

    @Autowired
    private RoomsFacade roomsFacade;

    @GetMapping("/{roomNumber}")
    public RoomDto getRoom(@PathVariable final String roomNumber) throws NotFoundException {
        final Optional<RoomDto> roomDtoOptional = roomsFacade.findRoomByNumber(roomNumber);
        if (!roomDtoOptional.isPresent()) {
            throw new NotFoundException("Room [" + roomNumber + "] not found.");
        }

        return roomDtoOptional.get();
    }

    @GetMapping
    public RoomsDto getRooms() throws NotFoundException {
        return roomsFacade.findRooms();
    }
}
