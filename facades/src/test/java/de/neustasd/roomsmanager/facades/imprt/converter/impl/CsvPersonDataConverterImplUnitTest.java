package de.neustasd.roomsmanager.facades.imprt.converter.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import de.neustasd.roomsmanager.core.services.ImportService;
import de.neustasd.roomsmanager.facades.imprt.converter.CsvPersonDataConverter;
import de.neustasd.roomsmanager.facades.imprt.csv.parser.ImportCsvParser;
import org.junit.Test;

/** Created by Adrian Tello on 09/12/2017. */
public class CsvPersonDataConverterImplUnitTest {

  private final CsvPersonDataConverter csvPersonDataConverter = new CsvPersonDataConverterImpl();

  @Test
  public void test1() {
    // Prepare
    final ImportCsvParser.CsvPersonData csvPersonData =
        ImportCsvParser.CsvPersonData.builder()
            .firstName("Firstname")
            .lastName("Lastname")
            .ldapUser("ldapuser")
            .build();

    // Test
    final ImportService.PersonData personData = csvPersonDataConverter.convert(csvPersonData);

    // Verify
    assertNotNull(personData);

    assertNull(personData.getTitle());
    assertEquals("Firstname", personData.getFirstName());
    assertNull(personData.getNameAddition());
    assertEquals("Lastname", personData.getLastName());
    assertEquals("ldapuser", personData.getLdapUser());
  }

  @Test
  public void test2() {
    // Prepare
    final ImportCsvParser.CsvPersonData csvPersonData =
        ImportCsvParser.CsvPersonData.builder()
            .title("Dr.")
            .firstName("Firstname")
            .nameAddition("von")
            .lastName("Lastname")
            .ldapUser("ldapuser")
            .build();

    // Test
    final ImportService.PersonData personData = csvPersonDataConverter.convert(csvPersonData);

    // Verify
    assertNotNull(personData);

    assertEquals("Dr.", personData.getTitle());
    assertEquals("Firstname", personData.getFirstName());
    assertEquals("von", personData.getNameAddition());
    assertEquals("Lastname", personData.getLastName());
    assertEquals("ldapuser", personData.getLdapUser());
  }
}
