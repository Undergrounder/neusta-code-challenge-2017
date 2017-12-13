package de.neusta_sd.roomsmanager.fatjar.controllers;

import de.neusta_sd.roomsmanager.facades.dto.PersonDto;
import de.neusta_sd.roomsmanager.facades.dto.RoomDto;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Adrian Tello on 11/12/2017.
 */
public class RoomsApiControllerIntegrationTest extends AbstractApiIntegrationTest{

    @Test
    public void testEmpty() throws IOException {
        doTestException(() -> getApiRoom("1234"), HttpStatus.NOT_FOUND, 5);
    }

    private ResponseEntity<RoomDto> getApiRoom(final String roomNumber) {
        final RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForEntity( getBaseUrl() + "/api/room/" + roomNumber, RoomDto.class);
    }

    @Test
    public void testInvalidMethod() {
        doTestInvalidMethod("/api/room/1107");
    }

    @Test
    public void testInvalidRoomsMethod() {
        doTestInvalidMethod("/api/room");
    }

    @Test
    public void testSingle(){
        //Prepare
        prepareSitzplanFile();

        //Test
        final ResponseEntity<RoomDto> roomDtoResponseEntity = getApiRoom("1107");
        assertEquals(HttpStatus.OK, roomDtoResponseEntity.getStatusCode());

        final RoomDto roomDto = roomDtoResponseEntity.getBody();
        assertRoom1107(roomDto);
    }

    private void assertRoom1107(final RoomDto roomDto)
    {
        //Prepare expectations
        final Set<PersonDto> expectedPersonDtos = new HashSet<>();

        final PersonDto cschuetteDto = PersonDto.builder()
                .firstName("Carsten")
                .lastName("Schütte")
                .ldapuser("cschuette")
                .build();
        expectedPersonDtos.add(cschuetteDto);

        final PersonDto celfersDto = PersonDto.builder()
                .firstName("Carsten")
                .lastName("Elfers")
                .ldapuser("celfers")
                .build();
        expectedPersonDtos.add(celfersDto);

        final PersonDto ndyminiDto = PersonDto.builder()
                .firstName("Nicole")
                .lastName("Dymini")
                .ldapuser("ndymini")
                .build();
        expectedPersonDtos.add(ndyminiDto);

        //Verify
        assertEquals("1107", roomDto.getRoom());

        final Set<PersonDto> personDtos = new HashSet<>(roomDto.getPeople());
        assertEquals(expectedPersonDtos, personDtos);
    }
}
