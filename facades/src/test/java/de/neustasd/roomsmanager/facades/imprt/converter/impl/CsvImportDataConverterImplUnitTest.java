package de.neustasd.roomsmanager.facades.imprt.converter.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import de.neustasd.roomsmanager.core.services.data.ImportData;
import de.neustasd.roomsmanager.core.services.data.RoomData;
import de.neustasd.roomsmanager.facades.imprt.converter.CsvImportDataConverter;
import de.neustasd.roomsmanager.facades.imprt.converter.CsvRoomDataConverter;
import de.neustasd.roomsmanager.facades.imprt.csv.parser.ImportCsvParser;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/** Created by Adrian Tello on 09/12/2017. */
@RunWith(MockitoJUnitRunner.class)
public class CsvImportDataConverterImplUnitTest {

  @Mock private CsvRoomDataConverter csvRoomDataConverter;

  private CsvImportDataConverter csvImportDataConverter;

  @Before
  public void setUp() throws Exception {
    csvImportDataConverter = new CsvImportDataConverterImpl(csvRoomDataConverter);
  }

  @Test
  public void testEmptyRoom() {
    // Prepare
    final ImportCsvParser.CsvImportData csvImportDataSource =
        ImportCsvParser.CsvImportData.builder().build();

    // Test
    final ImportData importData = csvImportDataConverter.convert(csvImportDataSource);

    // Verify
    assertNotNull(importData);

    final List<RoomData> roomDataList = importData.getRoomDataList();
    assertNotNull(roomDataList);

    assertEquals(0, roomDataList.size());
  }

  @Test
  public void testMultipleRooms() {
    // Prepare
    final ImportCsvParser.CsvRoomData csvRoomData1 =
        ImportCsvParser.CsvRoomData.builder().number("1111").build();
    final ImportCsvParser.CsvRoomData csvRoomData2 =
        ImportCsvParser.CsvRoomData.builder().number("1112").build();

    // Dummy results
    final RoomData roomData1 =
        RoomData.builder().number("1111").build();
    final RoomData roomData2 =
        RoomData.builder().number("1112").build();

    when(csvRoomDataConverter.convert(csvRoomData1)).thenReturn(roomData1);
    when(csvRoomDataConverter.convert(csvRoomData2)).thenReturn(roomData2);

    final ImportCsvParser.CsvImportData csvImportDataSource =
        ImportCsvParser.CsvImportData.builder()
            .roomData(csvRoomData1)
            .roomData(csvRoomData2)
            .build();

    // Test
    final ImportData importData = csvImportDataConverter.convert(csvImportDataSource);

    // Verify
    assertNotNull(importData);

    final List<RoomData> roomDataList = importData.getRoomDataList();
    assertNotNull(roomDataList);

    assertEquals(2, roomDataList.size());
    assertEquals(roomData1, roomDataList.get(0));
    assertEquals(roomData2, roomDataList.get(1));
  }
}
