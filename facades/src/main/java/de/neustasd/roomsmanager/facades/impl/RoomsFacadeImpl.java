package de.neustasd.roomsmanager.facades.impl;

import de.neustasd.roomsmanager.core.entities.Room;
import de.neustasd.roomsmanager.core.services.RoomsService;
import de.neustasd.roomsmanager.facades.RoomsFacade;
import de.neustasd.roomsmanager.facades.converters.RoomConverter;
import de.neustasd.roomsmanager.facades.converters.RoomsConverter;
import de.neustasd.roomsmanager.facades.dto.RoomDto;
import de.neustasd.roomsmanager.facades.dto.RoomsDto;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
import org.springframework.stereotype.Component;

/** Created by Adrian Tello on 05/12/2017. */
@Component
public class RoomsFacadeImpl implements RoomsFacade {

  private final RoomsService roomsService;

  private final RoomConverter roomConverter;

  private final RoomsConverter roomsConverter;

  public RoomsFacadeImpl(
      RoomsService roomsService, RoomConverter roomConverter, RoomsConverter roomsConverter) {
    this.roomsService = roomsService;
    this.roomConverter = roomConverter;
    this.roomsConverter = roomsConverter;
  }

  @Override
  public Optional<RoomDto> findRoomByNumber(final String number) throws InvalidRoomNumberException {
    Optional<RoomDto> roomDtoOptional = Optional.empty();

    try {
      final Optional<Room> roomOptional = getRoomsService().findRoomByNumber(number);
      if (roomOptional.isPresent()) {
        final Room room = roomOptional.get();
        final RoomDto roomDto = getRoomConverter().convert(room);
        roomDtoOptional = Optional.of(roomDto);
      }

      return roomDtoOptional;
    } catch (ConstraintViolationException e) {
      throw new InvalidRoomNumberException("Invalid room number [" + number + "].");
    }
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
