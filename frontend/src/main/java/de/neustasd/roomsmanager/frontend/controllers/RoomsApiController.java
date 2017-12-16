package de.neustasd.roomsmanager.frontend.controllers;

import de.neustasd.roomsmanager.facades.RoomsFacade;
import de.neustasd.roomsmanager.facades.dto.RoomDto;
import de.neustasd.roomsmanager.facades.dto.RoomsDto;
import de.neustasd.roomsmanager.frontend.controllers.exceptions.MethodNotAllowedException;
import de.neustasd.roomsmanager.frontend.controllers.exceptions.NotFoundException;
import de.neustasd.roomsmanager.frontend.controllers.exceptions.RoomNotFoundException;
import de.neustasd.roomsmanager.frontend.dto.ExceptionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static de.neustasd.roomsmanager.frontend.controllers.RoomsApiController.BASE_PATH;

/** Created by Adrian Tello on 05/12/2017. */
@RestController
@RequestMapping(BASE_PATH)
public class RoomsApiController extends AbstractApiController {

  public static final String BASE_PATH = ApiController.BASE_PATH + "/room";

  @Autowired private RoomsFacade roomsFacade;

  @GetMapping("/{roomNumber}")
  public RoomDto getRoom(@PathVariable final String roomNumber)
      throws NotFoundException, RoomsFacade.InvalidRoomNumberException {
    final Optional<RoomDto> roomDtoOptional = roomsFacade.findRoomByNumber(roomNumber);
    if (!roomDtoOptional.isPresent()) {
      throw new RoomNotFoundException("Room [" + roomNumber + "] not found.");
    }

    return roomDtoOptional.get();
  }

  @RequestMapping("/{roomNumber}")
  public void othersRoom(@PathVariable final String roomNumber)
      throws NotFoundException, MethodNotAllowedException {
    throw new MethodNotAllowedException();
  }

  @GetMapping
  public RoomsDto getRooms() throws NotFoundException {
    return roomsFacade.findRooms();
  }

  @RequestMapping
  public void othersRooms() throws MethodNotAllowedException {
    throw new MethodNotAllowedException();
  }

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