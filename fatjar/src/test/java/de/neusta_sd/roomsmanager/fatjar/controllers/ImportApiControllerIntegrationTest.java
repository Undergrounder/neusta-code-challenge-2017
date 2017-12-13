package de.neusta_sd.roomsmanager.fatjar.controllers;

import de.neusta_sd.roomsmanager.core.entities.Person;
import de.neusta_sd.roomsmanager.core.entities.Room;
import de.neusta_sd.roomsmanager.core.services.PersonsService;
import de.neusta_sd.roomsmanager.core.services.RoomsService;
import de.neusta_sd.roomsmanager.facades.ImportFacade;
import de.neusta_sd.roomsmanager.facades.dto.ImportResultDto;
import de.neusta_sd.roomsmanager.fatjar.FatJarApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * Created by Adrian Tello on 09/12/2017.
 */

public class ImportApiControllerIntegrationTest extends AbstractApiIntegrationTest{

    @Autowired
    private RoomsService roomsService;

    @Autowired
    private PersonsService personsService;

    @Test
    public void testEmptyFile() throws IOException {
        //Test
        final ResponseEntity<ImportResultDto> responseEntity = postResource("/imports/empty.csv");

        //Verify
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());

        final ImportResultDto importResultDto = responseEntity.getBody();
        assertNotNull(importResultDto);
        assertEquals(0, importResultDto.getRooms());
        assertEquals(0, importResultDto.getPersons());

        //Verify database
        final List<Room> roomList = roomsService.findAll();
        assertNotNull(roomList);
        assertEquals(0, roomList.size());

        final List<Person> personList = personsService.findAll();
        assertNotNull(personList);
        assertEquals(0, personList.size());
    }

    @Test
    public void testExampleFile() throws IOException {
        //Test
        final ResponseEntity<ImportResultDto> responseEntity = importSitzplanFile();

        //Verify
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());

        final ImportResultDto importResultDto = responseEntity.getBody();
        assertNotNull(importResultDto);
        assertEquals(15, importResultDto.getRooms());
        assertEquals(49, importResultDto.getPersons());

        //Verify database
        final List<Room> roomList = roomsService.findAll();
        assertNotNull(roomList);
        assertEquals(15, roomList.size());

        final List<Person> personList = personsService.findAll();
        assertNotNull(personList);
        assertEquals(49, personList.size());
    }

    @Test
    public void importChain() throws IOException {
        testExampleFile();
        testEmptyFile();
    }
}
