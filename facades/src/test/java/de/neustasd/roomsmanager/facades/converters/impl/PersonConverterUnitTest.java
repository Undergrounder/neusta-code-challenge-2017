package de.neustasd.roomsmanager.facades.converters.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import de.neustasd.roomsmanager.core.entities.NameAddition;
import de.neustasd.roomsmanager.core.entities.Person;
import de.neustasd.roomsmanager.core.entities.Title;
import de.neustasd.roomsmanager.facades.converters.PersonConverter;
import de.neustasd.roomsmanager.facades.dto.PersonDto;
import org.junit.Test;

/** Created by Adrian Tello on 16/12/2017. */
public class PersonConverterUnitTest {

  private PersonConverter personConverter = new PersonConverterImpl();

  @Test
  public void testWithoutOptionals() {
    // Prepare
    final Person person = new Person();
    person.setFirstName("Sebastian");
    person.setLastName("Foot");
    person.setLdapUser("sfoot");

    // Test
    final PersonDto personDto = personConverter.convert(person);

    // Verify
    assertNotNull(personDto);
    assertNull(personDto.getTitle());
    assertEquals(person.getFirstName(), personDto.getFirstName());
    assertNull(person.getNameAddition());
    assertEquals(person.getLastName(), personDto.getLastName());
    assertEquals(person.getLdapUser(), personDto.getLdapuser());
  }

  @Test
  public void testWithOptionals() {
    // Prepare
    final Title title = new Title();
    title.setName("Dr.");

    final NameAddition nameAddition = new NameAddition();
    nameAddition.setName("von");

    final Person person = new Person();
    person.setTitle(title);
    person.setFirstName("Sebastian");
    person.setNameAddition(nameAddition);
    person.setLastName("Foot");
    person.setLdapUser("sfoot");

    // Test
    final PersonDto personDto = personConverter.convert(person);

    // Verify
    assertNotNull(personDto);
    assertEquals(title.getName(), personDto.getTitle());
    assertEquals(person.getFirstName(), personDto.getFirstName());
    assertEquals(nameAddition.getName(), personDto.getNameAddition());
    assertEquals(person.getLastName(), personDto.getLastName());
    assertEquals(person.getLdapUser(), personDto.getLdapuser());
  }
}
