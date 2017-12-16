package de.neustasd.roomsmanager.core.services.converters.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import de.neustasd.roomsmanager.core.entities.Room;
import de.neustasd.roomsmanager.core.services.ImportService;
import de.neustasd.roomsmanager.core.services.converters.Converter;
import de.neustasd.roomsmanager.core.services.converters.RoomDataConverter;
import org.junit.Test;

/** Created by Adrian Tello on 01/12/2017. */
public class RoomDataConverterImplUnitTest {

  private final RoomDataConverter roomDataConverter = new RoomDataConverterImpl();

  @Test
  public void testConversion() throws Converter.ConversionException {
    // Prepare
    final String roomNumber = "1234";
    final ImportService.RoomData roomData =
        ImportService.RoomData.builder().number(roomNumber).build();

    // Test
    final Room room = roomDataConverter.convert(roomData);

    // Verify
    assertNotNull(room);
    assertEquals(roomNumber, room.getNumber());
  }
}
