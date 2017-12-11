package de.neusta_sd.roomsmanager.fatjar.controllers;

import de.neusta_sd.roomsmanager.core.entities.Person;
import de.neusta_sd.roomsmanager.core.entities.Room;
import de.neusta_sd.roomsmanager.core.services.PersonsService;
import de.neusta_sd.roomsmanager.core.services.RoomsService;
import de.neusta_sd.roomsmanager.facades.ImportFacade;
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
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FatJarApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = FatJarApplication.class)
public class ImportApiControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private RoomsService roomsService;

    @Autowired
    private PersonsService personsService;

    @Test
    public void testEmptyFile() throws IOException {
        //Test
        final ResponseEntity<ImportFacade.PostImportResultData> responseEntity = postResource("/imports/empty.csv");

        //Verify
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

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
        final ResponseEntity<ImportFacade.PostImportResultData> responseEntity = postResource("/imports/sitzplan.csv");

        //Verify
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        //Verify database
        final List<Room> roomList = roomsService.findAll();
        assertNotNull(roomList);
        assertEquals(15, roomList.size());

        final List<Person> personList = personsService.findAll();
        assertNotNull(personList);
        assertEquals(49, personList.size());
    }

    private ResponseEntity<ImportFacade.PostImportResultData> postResource(final String path) {
        final MultiValueMap<String, Object> multipartRequest = new LinkedMultiValueMap<>();

        final HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.MULTIPART_FORM_DATA);

        final HttpHeaders fileHeader = new HttpHeaders();
        fileHeader.setContentType(MediaType.parseMediaType("text/csv"));

        final Resource resource = new ClassPathResource(path);
        final HttpEntity<Resource> importFileEntity = new HttpEntity<>(resource, fileHeader);

        multipartRequest.add("file", importFileEntity);

        final HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(multipartRequest, header);
        final RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity("http://localhost:" + port + "/api/import", requestEntity, ImportFacade.PostImportResultData.class);
    }
}
