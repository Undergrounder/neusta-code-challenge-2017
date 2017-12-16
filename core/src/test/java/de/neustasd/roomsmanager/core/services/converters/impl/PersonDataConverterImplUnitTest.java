package de.neustasd.roomsmanager.core.services.converters.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import de.neustasd.roomsmanager.core.entities.NameAddition;
import de.neustasd.roomsmanager.core.entities.Person;
import de.neustasd.roomsmanager.core.entities.Title;
import de.neustasd.roomsmanager.core.services.ImportService;
import de.neustasd.roomsmanager.core.services.converters.Converter;
import de.neustasd.roomsmanager.core.services.converters.NameAdditionNameConverter;
import de.neustasd.roomsmanager.core.services.converters.PersonDataConverter;
import de.neustasd.roomsmanager.core.services.converters.TitleNameConverter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/** Created by Adrian Tello on 01/12/2017. */
@RunWith(MockitoJUnitRunner.class)
public class PersonDataConverterImplUnitTest {

  @Mock private NameAdditionNameConverter nameAdditionNameConverter;

  @Mock private TitleNameConverter titleNameConverter;

  private PersonDataConverter personDataConverter;

  @Before
  public void setUp() throws Exception {
    personDataConverter =
        new PersonDataConverterImpl(nameAdditionNameConverter, titleNameConverter);
  }

  @Test
  public void testConvertAllFields() throws Converter.ConversionException {
    // Prepare
    {
      final Title convertedTitle = new Title();
      convertedTitle.setName("Dr.");

      when(titleNameConverter.convert("Dr.")).thenReturn(convertedTitle);
    }

    {
      final NameAddition nameAddition = new NameAddition();
      nameAddition.setName("von");

      when(nameAdditionNameConverter.convert("von")).thenReturn(nameAddition);
    }

    // Test
    final ImportService.PersonData personData =
        ImportService.PersonData.builder()
            .title("Dr.")
            .firstName("Max")
            .nameAddition("von")
            .lastName("Mustermann")
            .ldapUser("mmustermann")
            .build();

    final Person convertedPerson = personDataConverter.convert(personData);

    // Verify
    assertNotNull(convertedPerson);

    assertEquals("Max", convertedPerson.getFirstName());
    assertEquals("Mustermann", convertedPerson.getLastName());
    assertEquals("mmustermann", convertedPerson.getLdapUser());

    {
      final Title title = convertedPerson.getTitle();
      assertNotNull(title);
      assertEquals("Dr.", title.getName());
    }
    {
      final NameAddition nameAddition = convertedPerson.getNameAddition();
      assertNotNull(nameAddition);
      assertEquals("von", nameAddition.getName());
    }
  }

  @Test
  public void testConvertWithoutNullableFields() throws Converter.ConversionException {
    // Test
    final ImportService.PersonData personData =
        ImportService.PersonData.builder()
            .title("Dr.")
            .firstName("Max")
            .nameAddition("von")
            .lastName("Mustermann")
            .ldapUser("mmustermann")
            .build();

    final Person convertedPerson = personDataConverter.convert(personData);

    // Verify
    assertNotNull(convertedPerson);

    assertNull(convertedPerson.getTitle());
    assertEquals("Max", convertedPerson.getFirstName());
    assertNull(convertedPerson.getNameAddition());
    assertEquals("Mustermann", convertedPerson.getLastName());
    assertEquals("mmustermann", convertedPerson.getLdapUser());
  }
}
