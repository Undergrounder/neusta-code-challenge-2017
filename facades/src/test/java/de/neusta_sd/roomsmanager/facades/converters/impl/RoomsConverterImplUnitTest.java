package de.neusta_sd.roomsmanager.facades.converters.impl;

import de.neusta_sd.roomsmanager.core.entities.Person;
import de.neusta_sd.roomsmanager.core.entities.Room;
import de.neusta_sd.roomsmanager.facades.converters.RoomConverter;
import de.neusta_sd.roomsmanager.facades.converters.RoomsConverter;
import de.neusta_sd.roomsmanager.facades.dto.PersonDto;
import de.neusta_sd.roomsmanager.facades.dto.RoomDto;
import de.neusta_sd.roomsmanager.facades.dto.RoomsDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

/** Created by Adrian Tello on 11/12/2017. */
@RunWith(MockitoJUnitRunner.class)
public class RoomsConverterImplUnitTest {

  @Mock private RoomConverter roomConverter;

  private RoomsConverter roomsConverter;

  @Before
  public void setUp() throws Exception {
    roomsConverter = new RoomsConverterImpl(roomConverter);
  }

  @Test
  public void testEmpty() {
    // Prepare
    final List<Room> source = Collections.emptyList();

    // Test
    final RoomsDto roomsDto = roomsConverter.convert(source);

    // Verify
    assertNotNull(roomsDto);

    final List<RoomDto> roomDtos = roomsDto.getRooms();
    assertNotNull(roomDtos);
    assertEquals(0, roomDtos.size());
  }

  @Test
  public void testMultiple() {
    // Prepare
    final Person kweslingPerson = new Person();
    kweslingPerson.setFirstName("Kai");
    kweslingPerson.setLastName("Wesling");
    kweslingPerson.setLdapUser("kwesling");

    final Person tkrusePerson = new Person();
    tkrusePerson.setFirstName("Thomas");
    tkrusePerson.setLastName("Kruse");
    tkrusePerson.setLdapUser("tkruse");

    final Room room1108 = new Room();
    room1108.setNumber("1108");
    room1108.setPeople(Arrays.asList(kweslingPerson, tkrusePerson));

    final Person cschuettePerson = new Person();
    cschuettePerson.setFirstName("Carsten");
    cschuettePerson.setLastName("Schütte");
    cschuettePerson.setLdapUser("cschuette");

    final Person celfersPerson = new Person();
    celfersPerson.setFirstName("Carsten");
    celfersPerson.setLastName("Elfers");
    celfersPerson.setLdapUser("celfers");

    final Person ndyminiPerson = new Person();
    ndyminiPerson.setFirstName("Nicole");
    ndyminiPerson.setLastName("Dymini");
    ndyminiPerson.setLdapUser("ndymini");

    final Room room1107 = new Room();
    room1107.setNumber("1107");
    room1107.setPeople(Arrays.asList(cschuettePerson, celfersPerson, ndyminiPerson));

    final List<Room> source = Arrays.asList(room1108, room1107);

    // Prepare expectations
    final PersonDto kweslingPersonDto =
        PersonDto.builder().firstName("Kai").lastName("Wesling").ldapuser("kwesling").build();

    final PersonDto tkrusePersonDto =
        PersonDto.builder().firstName("Thomas").lastName("Kruse").ldapuser("tkruse").build();

    final RoomDto roomDto1108 =
        RoomDto.builder().room("1108").person(kweslingPersonDto).person(tkrusePersonDto).build();

    final PersonDto cschuettePersonDto =
        PersonDto.builder().firstName("Carsten").lastName("Schütte").ldapuser("cschuette").build();

    final PersonDto celfersPersonDto =
        PersonDto.builder().firstName("Carsten").lastName("Elfers").ldapuser("celfers").build();

    final PersonDto ndyminiPersonDto =
        PersonDto.builder().firstName("Nicole").lastName("Dymini").ldapuser("ndymini").build();

    final RoomDto roomDto1107 =
        RoomDto.builder()
            .room("1107")
            .person(cschuettePersonDto)
            .person(celfersPersonDto)
            .person(ndyminiPersonDto)
            .build();

    final Set<RoomDto> expectedRoomDtosSet = new HashSet<>();
    expectedRoomDtosSet.add(roomDto1107);
    expectedRoomDtosSet.add(roomDto1108);

    when(roomConverter.convert(room1107)).thenReturn(roomDto1107);
    when(roomConverter.convert(room1108)).thenReturn(roomDto1108);

    // Test
    final RoomsDto roomsDto = roomsConverter.convert(source);

    // Verify
    assertNotNull(roomsDto);

    final List<RoomDto> roomDtos = roomsDto.getRooms();
    assertNotNull(roomDtos);
    assertEquals(2, roomDtos.size());

    assertEquals(expectedRoomDtosSet, new HashSet<>(roomDtos));
  }
}
