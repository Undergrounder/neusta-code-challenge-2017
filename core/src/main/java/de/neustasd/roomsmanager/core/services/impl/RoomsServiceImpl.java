package de.neustasd.roomsmanager.core.services.impl;

import de.neustasd.roomsmanager.core.entities.Room;
import de.neustasd.roomsmanager.core.entities.repositories.RoomRepository;
import de.neustasd.roomsmanager.core.services.RoomsService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/** Created by Adrian Tello on 05/12/2017. */
@Service
@Validated
public class RoomsServiceImpl implements RoomsService {

  private final RoomRepository roomRepository;

  @Autowired
  public RoomsServiceImpl(final RoomRepository roomRepository) {
    this.roomRepository = roomRepository;
  }

  @Override
  public Optional<Room> findRoomByNumber(String number) {
    return getRoomRepository().findOneByNumber(number);
  }

  @Override
  public List<Room> findAll() {
    return getRoomRepository().findAll();
  }

  private RoomRepository getRoomRepository() {
    return roomRepository;
  }
}
