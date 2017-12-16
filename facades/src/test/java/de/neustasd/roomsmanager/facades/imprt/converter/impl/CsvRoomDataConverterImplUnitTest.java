package de.neustasd.roomsmanager.facades.imprt.converter.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import de.neustasd.roomsmanager.core.services.data.PersonData;
import de.neustasd.roomsmanager.core.services.data.RoomData;
import de.neustasd.roomsmanager.facades.imprt.converter.CsvPersonDataConverter;
import de.neustasd.roomsmanager.facades.imprt.converter.CsvRoomDataConverter;
import de.neustasd.roomsmanager.facades.imprt.csv.parser.ImportCsvParser;
import de.neustasd.roomsmanager.facades.imprt.csv.parser.data.CsvPersonData;
import de.neustasd.roomsmanager.facades.imprt.csv.parser.data.CsvRoomData;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/** Created by Adrian Tello on 09/12/2017. */
@RunWith(MockitoJUnitRunner.class)
public class CsvRoomDataConverterImplUnitTest {

  @Mock private CsvPersonDataConverter csvPersonDataConverter;

  private CsvRoomDataConverter csvRoomDataConverter;

  @Before
  public void setUp() throws Exception {
    csvRoomDataConverter = new CsvRoomDataConverterImpl(csvPersonDataConverter);
  }

  @Test
  public void testEmptyRoom() {
    // Prepare
    final CsvRoomData csvRoomData =
        CsvRoomData.builder().number("1234").build();

    // Test
    final RoomData roomData = csvRoomDataConverter.convert(csvRoomData);

    // Verify
    assertNotNull(roomData);

    final List<PersonData> personDataList = roomData.getPersonDataList();
    assertNotNull(personDataList);

    assertEquals(0, personDataList.size());
  }

  @Test
  public void testRoom() {
    // Prepare
    final CsvPersonData csvPersonData1 =
            CsvPersonData.builder()
            .firstName("Adrian")
            .lastName("Tello")
            .ldapUser("a.lasheras")
            .build();
    final CsvPersonData csvPersonData2 =
            CsvPersonData.builder()
            .title("Dr.")
            .firstName("Vorname")
            .lastName("Nachname")
            .ldapUser("vorname.nachname")
            .build();

    final CsvRoomData csvRoomData =
            CsvRoomData.builder()
            .number("1234")
            .personData(csvPersonData1)
            .personData(csvPersonData2)
            .build();

    // Prepare results
    final PersonData personData1 =
        PersonData.builder()
            .firstName("Adrian")
            .lastName("Tello")
            .ldapUser("a.lasheras")
            .build();
    final PersonData personData2 =
        PersonData.builder()
            .title("Dr.")
            .firstName("Vorname")
            .lastName("Nachname")
            .ldapUser("vorname.nachname")
            .build();

    when(csvPersonDataConverter.convert(csvPersonData1)).thenReturn(personData1);
    when(csvPersonDataConverter.convert(csvPersonData2)).thenReturn(personData2);

    // Test
    final RoomData roomData = csvRoomDataConverter.convert(csvRoomData);

    // Verify
    assertNotNull(roomData);
    assertEquals("1234", roomData.getNumber());

    final List<PersonData> personDataList = roomData.getPersonDataList();
    assertNotNull(personDataList);

    assertEquals(2, personDataList.size());
    assertEquals(personData1, personDataList.get(0));
    assertEquals(personData2, personDataList.get(1));
  }
}
