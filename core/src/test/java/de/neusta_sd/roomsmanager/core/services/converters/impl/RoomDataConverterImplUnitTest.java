package de.neusta_sd.roomsmanager.core.services.converters.impl;

import de.neusta_sd.roomsmanager.core.entities.Room;
import de.neusta_sd.roomsmanager.core.services.ImportService;
import de.neusta_sd.roomsmanager.core.services.converters.Converter;
import de.neusta_sd.roomsmanager.core.services.converters.RoomDataConverter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
