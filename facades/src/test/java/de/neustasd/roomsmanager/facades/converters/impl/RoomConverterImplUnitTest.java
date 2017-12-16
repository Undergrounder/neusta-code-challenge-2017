package de.neustasd.roomsmanager.facades.converters.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import de.neustasd.roomsmanager.core.entities.Person;
import de.neustasd.roomsmanager.core.entities.Room;
import de.neustasd.roomsmanager.facades.converters.PersonConverter;
import de.neustasd.roomsmanager.facades.converters.RoomConverter;
import de.neustasd.roomsmanager.facades.dto.PersonDto;
import de.neustasd.roomsmanager.facades.dto.RoomDto;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Unit-Tests for the RoomConverter.
 */
@RunWith(MockitoJUnitRunner.class)
public class RoomConverterImplUnitTest {

  @Mock
  private PersonConverter personConverter;

  private RoomConverter roomConverter;

  /**
   * SetUp tests
   * @throws Exception
   */
  @Before
  public void setUp() throws Exception {
    roomConverter = new RoomConverterImpl(personConverter);
  }

  /**
   * Tests the conversion of an empty room.
   */
  @Test
  public void testEmptyRoom() {
    //Prepare
    final Room room = new Room();
    room.setNumber("1111");

    //Test
    final RoomDto roomDto = roomConverter.convert(room);

    //Verify
    assertNotNull(roomDto);
    assertEquals("1111", roomDto.getRoom());

    final List<PersonDto> personDtoList = roomDto.getPeople();
    assertNotNull(personDtoList);
    assertEquals(0, personDtoList.size());
  }

  /**
   * Tests the conversion of an room with one person.
   */
  @Test
  public void testRoom() {
    //Prepare
    final Person person = new Person();
    person.setFirstName("Dennis");
    person.setLastName("Fischer");
    person.setLdapUser("dfischer");

    final Room room = new Room();
    room.setNumber("1111");
    room.setPeople(Collections.singletonList(person));

    final PersonDto convertedPerson = PersonDto.builder()
        .firstName("Dennis")
        .lastName("Fischer")
        .ldapuser("dfischer")
        .build();

    when(personConverter.convert(person)).thenReturn(convertedPerson);

    //Test
    final RoomDto roomDto = roomConverter.convert(room);

    //Verify
    assertNotNull(roomDto);
    assertEquals("1111", roomDto.getRoom());

    final List<PersonDto> personDtoList = roomDto.getPeople();
    assertNotNull(personDtoList);
    assertEquals(1, personDtoList.size());
    assertEquals(convertedPerson, personDtoList.get(0));
  }
}
