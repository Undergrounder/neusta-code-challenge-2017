package de.neusta_sd.roomsmanager.fatjar.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.neusta_sd.roomsmanager.core.entities.Person;
import de.neusta_sd.roomsmanager.core.entities.Room;
import de.neusta_sd.roomsmanager.core.entities.repositories.PersonRepository;
import de.neusta_sd.roomsmanager.core.entities.repositories.RoomRepository;
import de.neusta_sd.roomsmanager.core.services.PersonsService;
import de.neusta_sd.roomsmanager.core.services.RoomsService;
import de.neusta_sd.roomsmanager.facades.dto.ImportResultDto;
import de.neusta_sd.roomsmanager.facades.dto.RoomDto;
import de.neusta_sd.roomsmanager.fatjar.FatJarApplication;
import de.neusta_sd.roomsmanager.frontend.dto.ExceptionDto;
import org.junit.Before;
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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/** Created by Adrian Tello on 11/12/2017. */
@RunWith(SpringRunner.class)
@SpringBootTest(
  classes = FatJarApplication.class,
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ContextConfiguration(classes = FatJarApplication.class)
public abstract class AbstractApiIntegrationTest {

  @LocalServerPort private int port;

  @Autowired private RoomsService roomsService;

  @Autowired private PersonsService personsService;

  @Autowired private ObjectMapper objectMapper;

  @Autowired private PersonRepository personRepository;

  @Autowired private RoomRepository roomRepository;

  @Before
  public void setUp() throws Exception {
    personRepository.deleteAllInBatch();
    roomRepository.deleteAllInBatch();
  }

  protected ResponseEntity<ImportResultDto> postResource(final String path) {
    final MultiValueMap<String, Object> multipartRequest = new LinkedMultiValueMap<>();

    final HttpHeaders header = new HttpHeaders();
    header.setContentType(MediaType.MULTIPART_FORM_DATA);

    final HttpHeaders fileHeader = new HttpHeaders();
    fileHeader.setContentType(MediaType.parseMediaType("text/csv"));

    final Resource resource = new ClassPathResource(path);
    final HttpEntity<Resource> importFileEntity = new HttpEntity<>(resource, fileHeader);

    multipartRequest.add("file", importFileEntity);

    final HttpEntity<MultiValueMap<String, Object>> requestEntity =
        new HttpEntity<>(multipartRequest, header);
    final RestTemplate restTemplate = new RestTemplate();
    return restTemplate.postForEntity(
        getBaseUrl() + "/api/import", requestEntity, ImportResultDto.class);
  }

  protected ResponseEntity<ImportResultDto> importSitzplanFile() {
    return postResource("/imports/sitzplan.csv");
  }

  protected ResponseEntity<ImportResultDto> prepareSitzplanFile() {
    final ResponseEntity<ImportResultDto> importResultDtoResponseEntity = importSitzplanFile();

    assertEquals(HttpStatus.ACCEPTED, importResultDtoResponseEntity.getStatusCode());

    final ImportResultDto importResultDto = importResultDtoResponseEntity.getBody();
    assertNotNull(importResultDto);
    assertEquals(15, importResultDto.getRooms());
    assertEquals(49, importResultDto.getPersons());

    return importResultDtoResponseEntity;
  }

  protected int getPort() {
    return port;
  }

  protected String getBaseUrl() {
    return "http://localhost:" + getPort();
  }

  protected void doTestInvalidMethod(String url) {
    // Prepare
    final RestTemplate restTemplate = new RestTemplate();
    try {
      // Test
      restTemplate.postForEntity(getBaseUrl() + url, null, RoomDto.class, Collections.emptyMap());

      // Verify
      assertTrue(false); // Should never be called
    } catch (HttpClientErrorException e) {
      assertEquals(HttpStatus.METHOD_NOT_ALLOWED, e.getStatusCode());
    }
  }

  protected void doTestException(Runnable runnable, HttpStatus httpStatus, int expectedBodyCode)
      throws IOException {
    try {
      // Test
      runnable.run();

      // Verify
      assertFalse(true); // Should never get called
    } catch (final HttpClientErrorException clientErrorException) {
      assertEquals(httpStatus, clientErrorException.getStatusCode());

      final byte[] responseBody = clientErrorException.getResponseBodyAsByteArray();
      final ExceptionDto exceptionDto = objectMapper.readValue(responseBody, ExceptionDto.class);

      assertNotNull(exceptionDto);
      assertEquals(expectedBodyCode, exceptionDto.getCode());
      assertNotNull(exceptionDto.getMessage());
    }

    // No inserts have been done
    assertNoRoomsInDatabase();
    assertNoPersonsInDatabase();
  }

  protected void assertNoRoomsInDatabase() {
    final List<Room> roomList = roomsService.findAll();
    assertNotNull(roomList);
    assertEquals(0, roomList.size());
  }

  protected void assertNoPersonsInDatabase() {
    final List<Person> personList = personsService.findAll();
    assertNotNull(personList);
    assertEquals(0, personList.size());
  }

  protected RoomsService getRoomsService() {
    return roomsService;
  }

  protected PersonsService getPersonsService() {
    return personsService;
  }

  protected ObjectMapper getObjectMapper() {
    return objectMapper;
  }
}
