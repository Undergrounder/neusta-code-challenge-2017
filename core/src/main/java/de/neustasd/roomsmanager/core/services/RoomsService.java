package de.neustasd.roomsmanager.core.services;

import de.neustasd.roomsmanager.core.entities.Room;
import de.neustasd.roomsmanager.core.services.constraints.RoomNumberConstraint;

import java.util.List;
import java.util.Optional;

/** Created by Adrian Tello on 05/12/2017. */
public interface RoomsService {
  Optional<Room> findRoomByNumber(@RoomNumberConstraint final String number);

  List<Room> findAll();
}
