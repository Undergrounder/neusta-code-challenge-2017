package de.neustasd.roomsmanager.facades.imprt.csv.parser.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import de.neustasd.roomsmanager.facades.imprt.csv.parser.ImportCsvParser;
import de.neustasd.roomsmanager.facades.imprt.csv.parser.converter.PersonStringConverter;
import de.neustasd.roomsmanager.facades.imprt.csv.parser.data.CsvImportData;
import de.neustasd.roomsmanager.facades.imprt.csv.parser.data.CsvPersonData;
import de.neustasd.roomsmanager.facades.imprt.csv.parser.data.CsvRoomData;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/** Created by Adrian Tello on 09/12/2017. */
@RunWith(MockitoJUnitRunner.class)
public class ImportCsvParserImplUnitTest {

  @Mock private PersonStringConverter personStringConverter;

  private ImportCsvParser importCsvParser;

  @Before
  public void setUp() throws Exception {
    importCsvParser = new ImportCsvParserImpl(personStringConverter);
  }

  @Test
  public void testEmptyStream() throws ImportCsvParser.CsvParsingException, IOException {
    // Prepare
    try (InputStream inputStream = createInputStream("")) {
      // Test
      final CsvImportData csvImportData = importCsvParser.parse(inputStream);

      // Verify
      assertNotNull(csvImportData);
      assertNotNull(csvImportData.getRoomDataList());
      assertEquals(0, csvImportData.getRoomDataList().size());
    }
  }

  @Test
  public void testEmptyRoom() throws ImportCsvParser.CsvParsingException, IOException {
    // Prepare
    try (InputStream inputStream = createInputStream("1102,,,,")) {
      // Test
      final CsvImportData csvImportData = importCsvParser.parse(inputStream);

      // Verify
      assertNotNull(csvImportData);

      final List<CsvRoomData> roomDataList = csvImportData.getRoomDataList();
      assertNotNull(roomDataList);

      assertEquals(1, roomDataList.size());

      final CsvRoomData roomData = roomDataList.get(0);
      assertNotNull(roomData);

      assertEquals("1102", roomData.getNumber());

      // Persons list
      final List<CsvPersonData> csvPersonDataList = roomData.getPersonDataList();
      assertNotNull(csvPersonDataList);

      assertEquals(0, csvPersonDataList.size());
    }
  }

  @Test
  public void testMultiline() throws ImportCsvParser.CsvParsingException, IOException {
    // Prepare
    final CsvPersonData sborcherdingPersonData =
        CsvPersonData.builder()
            .firstName("Stefanie")
            .lastName("Borcherding")
            .ldapUser("sborcherding")
            .build();
    when(personStringConverter.convert("Stefanie Borcherding (sborcherding)"))
        .thenReturn(sborcherdingPersonData);

    final CsvPersonData thahnPersonData =
        CsvPersonData.builder().firstName("Tobias").lastName("Hahn").ldapUser("thahn").build();
    when(personStringConverter.convert("Tobias Hahn (thahn)")).thenReturn(thahnPersonData);

    final CsvPersonData delmPersonData =
        CsvPersonData.builder().firstName("Dominik").lastName("Elm").ldapUser("delm").build();
    when(personStringConverter.convert("Dominik Elm (delm)")).thenReturn(delmPersonData);

    final CsvPersonData kweslingPersonData =
        CsvPersonData.builder().firstName("Kai").lastName("Wesling").ldapUser("kwesling").build();
    when(personStringConverter.convert("Kai Wesling (kwesling)")).thenReturn(kweslingPersonData);

    final CsvPersonData tkrusePersonData =
        CsvPersonData.builder().firstName("Thomas").lastName("Kruse").ldapUser("tkruse").build();
    when(personStringConverter.convert("Thomas Kruse (tkruse)")).thenReturn(tkrusePersonData);

    final CsvPersonData cschuettePersonData =
        CsvPersonData.builder()
            .firstName("Carsten")
            .lastName("Schütte")
            .ldapUser("cschuette")
            .build();
    when(personStringConverter.convert("Carsten Schütte (cschuette)"))
        .thenReturn(cschuettePersonData);

    final CsvPersonData celfersPersonData =
        CsvPersonData.builder().firstName("Carsten").lastName("Elfers").ldapUser("celfers").build();
    when(personStringConverter.convert("Carsten Elfers (celfers)")).thenReturn(celfersPersonData);

    final CsvPersonData ndyminiPersonData =
        CsvPersonData.builder().firstName("Nicole").lastName("Dymini").ldapUser("ndymini").build();
    when(personStringConverter.convert("Nicole Dymini (ndymini)")).thenReturn(ndyminiPersonData);

    try (InputStream inputStream =
        createInputStream(
            "1109,Stefanie Borcherding (sborcherding),Tobias Hahn (thahn),Dominik Elm (delm),\n"
                + "1108,Kai Wesling (kwesling),Thomas Kruse (tkruse),,\n"
                + "1107,Carsten Schütte (cschuette),Carsten Elfers (celfers),Nicole Dymini (ndymini),")) {
      // Test
      final CsvImportData csvImportData = importCsvParser.parse(inputStream);

      // Verify
      assertNotNull(csvImportData);

      final List<CsvRoomData> roomDataList = csvImportData.getRoomDataList();
      assertNotNull(roomDataList);

      assertEquals(3, roomDataList.size());

      assertRoom(
          roomDataList.get(0),
          "1109",
          Arrays.asList(sborcherdingPersonData, thahnPersonData, delmPersonData));
      assertRoom(roomDataList.get(1), "1108", Arrays.asList(kweslingPersonData, tkrusePersonData));
      assertRoom(
          roomDataList.get(2),
          "1107",
          Arrays.asList(cschuettePersonData, celfersPersonData, ndyminiPersonData));
    }
  }

  private InputStream createInputStream(final String strSource) {
    final byte[] sourceBytes = strSource.getBytes(Charset.forName("UTF-8"));

    return new ByteArrayInputStream(sourceBytes);
  }

  private void assertRoom(
      CsvRoomData csvRoomData, String expectedRoomNumber, List<CsvPersonData> csvPersonData) {
    assertNotNull(csvPersonData);

    assertEquals(expectedRoomNumber, csvRoomData.getNumber());

    assertEquals(csvPersonData, csvRoomData.getPersonDataList());
  }
}
