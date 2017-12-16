package de.neustasd.roomsmanager.facades.imprt.converter;

import de.neustasd.roomsmanager.core.services.converters.Converter;
import de.neustasd.roomsmanager.core.services.data.RoomData;
import de.neustasd.roomsmanager.facades.imprt.csv.parser.data.CsvRoomData;

/** Created by Adrian Tello on 09/12/2017. */
public interface CsvRoomDataConverter extends Converter<CsvRoomData, RoomData> {}
