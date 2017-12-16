package de.neustasd.roomsmanager.facades.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import de.neustasd.roomsmanager.core.entities.Room;
import de.neustasd.roomsmanager.core.services.RoomsService;
import de.neustasd.roomsmanager.facades.RoomsFacade;
import de.neustasd.roomsmanager.facades.converters.RoomConverter;
import de.neustasd.roomsmanager.facades.converters.RoomsConverter;
import de.neustasd.roomsmanager.facades.dto.RoomDto;
import de.neustasd.roomsmanager.facades.dto.RoomsDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Unit-Tests for RoomsFacadeImpl.
 */
@RunWith(MockitoJUnitRunner.class)
public class RoomsFacadeImplUnitTest {

  @Mock
  private RoomsService roomsService;

  @Mock
  private RoomConverter roomConverter;

  @Mock
  private RoomsConverter roomsConverter;

  private RoomsFacade roomsFacade;

  /**
   * SetUp tests
   *
   * @throws Exception
   */
  @Before
  public void setUp() throws Exception {
    roomsFacade = new RoomsFacadeImpl(
        roomsService,
        roomConverter,
        roomsConverter);
  }

  /**
   * Test the findRooms method.
   */
  @Test
  public void testFindRooms() {
    //Prepare
    final Room room = new Room();
    room.setNumber("1111");

    final List<Room> roomList = new ArrayList<>();
    roomList.add(room);

    when(roomsService.findAll()).thenReturn(roomList);

    final RoomDto roomDto = RoomDto.builder().room("1111").build();
    final RoomsDto roomsDto = RoomsDto.builder().room(roomDto).build();
    when(roomsConverter.convert(Collections.singletonList(room))).thenReturn(roomsDto);

    //Test
    final RoomsDto roomsDtoResult = roomsFacade.findRooms();

    //Verify
    assertNotNull(roomsDto);
    assertEquals(roomsDto, roomsDtoResult);
  }

  /**
   * Test the findRoom method, when it finds a room.
   *
   * @throws RoomsFacade.InvalidRoomNumberException
   */
  @Test
  public void testFindRoomAvailable() throws RoomsFacade.InvalidRoomNumberException {
    //Prepare
    final Room room = new Room();
    room.setNumber("1111");

    final Optional<Room> roomOptional = Optional.of(room);

    when(roomsService.findRoomByNumber("1111")).thenReturn(roomOptional);

    final RoomDto roomDto = RoomDto.builder().room("1111").build();
    when(roomConverter.convert(room)).thenReturn(roomDto);
    //Test
    final Optional<RoomDto> roomDtoOptional = roomsFacade.findRoomByNumber("1111");

    //Verify
    assertNotNull(roomDtoOptional);
    assertTrue(roomDtoOptional.isPresent());

    final RoomDto roomDtoResult = roomDtoOptional.get();
    assertEquals(roomDto, roomDtoResult);
  }

  /**
   * Test the findRoom method, when it doesn't find a room.
   *
   * @throws RoomsFacade.InvalidRoomNumberException
   */
  @Test
  public void testFindRoomNotAvailable() throws RoomsFacade.InvalidRoomNumberException {
    //Prepare
    final Optional<Room> roomOptional = Optional.empty();

    when(roomsService.findRoomByNumber("1111")).thenReturn(roomOptional);
    //Test
    final Optional<RoomDto> roomDtoOptional = roomsFacade.findRoomByNumber("1111");

    //Verify
    assertNotNull(roomDtoOptional);
    assertFalse(roomDtoOptional.isPresent());
  }

  /**
   * Test the findRoom method, with an invalid room number.
   *
   * @throws RoomsFacade.InvalidRoomNumberException
   */
  @Test(expected = RoomsFacade.InvalidRoomNumberException.class)
  public void testFindRoomConstraintException() throws RoomsFacade.InvalidRoomNumberException {
    //Prepare
    final ConstraintViolationException exception = mock(ConstraintViolationException.class);
    when(roomsService.findRoomByNumber("111")).thenThrow(exception);

    //Test
    roomsFacade.findRoomByNumber("111");
  }
}
