package de.neusta_sd.roomsmanager.facades.imprt.converter.impl;

import de.neusta_sd.roomsmanager.core.services.ImportService;
import de.neusta_sd.roomsmanager.facades.imprt.converter.CsvImportDataConverter;
import de.neusta_sd.roomsmanager.facades.imprt.converter.CsvRoomDataConverter;
import de.neusta_sd.roomsmanager.facades.imprt.csv.parser.ImportCsvParser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

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
    final ImportService.ImportData importData = csvImportDataConverter.convert(csvImportDataSource);

    // Verify
    assertNotNull(importData);

    final List<ImportService.RoomData> roomDataList = importData.getRoomDataList();
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
    final ImportService.RoomData roomData1 =
        ImportService.RoomData.builder().number("1111").build();
    final ImportService.RoomData roomData2 =
        ImportService.RoomData.builder().number("1112").build();

    when(csvRoomDataConverter.convert(csvRoomData1)).thenReturn(roomData1);
    when(csvRoomDataConverter.convert(csvRoomData2)).thenReturn(roomData2);

    final ImportCsvParser.CsvImportData csvImportDataSource =
        ImportCsvParser.CsvImportData.builder()
            .roomData(csvRoomData1)
            .roomData(csvRoomData2)
            .build();

    // Test
    final ImportService.ImportData importData = csvImportDataConverter.convert(csvImportDataSource);

    // Verify
    assertNotNull(importData);

    final List<ImportService.RoomData> roomDataList = importData.getRoomDataList();
    assertNotNull(roomDataList);

    assertEquals(2, roomDataList.size());
    assertEquals(roomData1, roomDataList.get(0));
    assertEquals(roomData2, roomDataList.get(1));
  }
}
