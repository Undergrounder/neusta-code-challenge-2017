package de.neustasd.roomsmanager.frontend.controllers;

import static de.neustasd.roomsmanager.frontend.controllers.RoomsApiController.BASE_PATH;

import de.neustasd.roomsmanager.facades.RoomsFacade;
import de.neustasd.roomsmanager.facades.dto.RoomDto;
import de.neustasd.roomsmanager.facades.dto.RoomsDto;
import de.neustasd.roomsmanager.frontend.controllers.exceptions.MethodNotAllowedException;
import de.neustasd.roomsmanager.frontend.controllers.exceptions.NotFoundException;
import de.neustasd.roomsmanager.frontend.controllers.exceptions.RoomNotFoundException;
import de.neustasd.roomsmanager.frontend.dto.ExceptionDto;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Created by Adrian Tello on 05/12/2017. */
@RestController
@RequestMapping(BASE_PATH)
public class RoomsApiController extends AbstractApiController {

  public static final String BASE_PATH = ApiController.BASE_PATH + "/room";

  @Autowired private RoomsFacade roomsFacade;

  /**
   * Get a room information by its number.
   *
   * @param roomNumber The room number
   *
   * @return The room information
   *
   * @throws NotFoundException If no room found
   * @throws RoomsFacade.InvalidRoomNumberException If the room number hasn't a valid format.
   */
  @GetMapping("/{roomNumber}")
  public RoomDto getRoom(@PathVariable final String roomNumber)
      throws NotFoundException, RoomsFacade.InvalidRoomNumberException {
    final Optional<RoomDto> roomDtoOptional = roomsFacade.findRoomByNumber(roomNumber);
    if (!roomDtoOptional.isPresent()) {
      throw new RoomNotFoundException("Room [" + roomNumber + "] not found.");
    }

    return roomDtoOptional.get();
  }

  /**
   * Catches all calls for non allowed methods and throws a MethodNotAllowedException.
   *
   * @throws MethodNotAllowedException
   */
  @RequestMapping("/{roomNumber}")
  public void othersRoom(@PathVariable final String roomNumber)
      throws NotFoundException, MethodNotAllowedException {
    throw new MethodNotAllowedException();
  }

  /**
   * Get all the rooms.
   *
   * @return Information about all the rooms
   */
  @GetMapping
  public RoomsDto getRooms() {
    return roomsFacade.findRooms();
  }

  /**
   * Catches all calls for non allowed methods and throws a MethodNotAllowedException.
   *
   * @throws MethodNotAllowedException
   */
  @RequestMapping
  public void othersRooms() throws MethodNotAllowedException {
    throw new MethodNotAllowedException();
  }

  /**
   *
   * {@inheritDoc }
   */
  @ExceptionHandler(value = {Exception.class})
  public ResponseEntity<ExceptionDto> exceptionHandler(final Exception e) {
    if (e instanceof RoomNotFoundException) {
      return createExceptionResponseEntity(404, 5, e.getMessage());
    } else if (e instanceof RoomsFacade.InvalidRoomNumberException) {
      return createExceptionResponseEntity(400, 6, e.getMessage());
    }

    return super.exceptionHandler(e);
  }
}
