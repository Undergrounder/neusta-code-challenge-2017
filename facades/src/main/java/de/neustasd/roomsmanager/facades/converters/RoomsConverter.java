package de.neustasd.roomsmanager.facades.converters;

import de.neustasd.roomsmanager.core.entities.Room;
import de.neustasd.roomsmanager.core.services.converters.Converter;
import de.neustasd.roomsmanager.facades.dto.RoomsDto;

import java.util.List;

/** Created by Adrian Tello on 11/12/2017. */
public interface RoomsConverter extends Converter<List<Room>, RoomsDto> {}
