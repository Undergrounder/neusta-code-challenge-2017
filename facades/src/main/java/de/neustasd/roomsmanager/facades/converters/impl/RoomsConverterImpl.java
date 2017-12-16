package de.neustasd.roomsmanager.facades.converters.impl;

import de.neustasd.roomsmanager.core.entities.Room;
import de.neustasd.roomsmanager.core.services.converters.ConverterUtils;
import de.neustasd.roomsmanager.facades.converters.RoomConverter;
import de.neustasd.roomsmanager.facades.converters.RoomsConverter;
import de.neustasd.roomsmanager.facades.dto.RoomDto;
import de.neustasd.roomsmanager.facades.dto.RoomsDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** Created by Adrian Tello on 11/12/2017. */
@Component
public class RoomsConverterImpl implements RoomsConverter {

  private final RoomConverter roomConverter;

  @Autowired
  public RoomsConverterImpl(RoomConverter roomConverter) {
    this.roomConverter = roomConverter;
  }

  @Override
  public RoomsDto convert(final List<Room> source) throws ConversionException {
    final List<RoomDto> roomDtos = ConverterUtils.convertAll(source, getRoomConverter());

    return RoomsDto.builder().rooms(roomDtos).build();
  }

  private RoomConverter getRoomConverter() {
    return roomConverter;
  }
}
