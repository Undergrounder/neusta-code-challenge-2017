package de.neustasd.roomsmanager.core.services.converters.impl;

import de.neustasd.roomsmanager.core.entities.Room;
import de.neustasd.roomsmanager.core.services.converters.RoomDataConverter;
import de.neustasd.roomsmanager.core.services.data.RoomData;
import org.springframework.stereotype.Component;

/** Created by Adrian Tello on 01/12/2017. */
@Component
public class RoomDataConverterImpl implements RoomDataConverter {

  @Override
  public Room convert(RoomData source) {
    final Room target = new Room();
    target.setNumber(source.getNumber());

    return target;
  }
}
