package de.neusta_sd.roomsmanager.fatjar.controllers;

import de.neusta_sd.roomsmanager.facades.dto.ImportResultDto;
import de.neusta_sd.roomsmanager.facades.dto.RoomDto;
import de.neusta_sd.roomsmanager.fatjar.FatJarApplication;
import org.junit.runner.RunWith;
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

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Adrian Tello on 11/12/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FatJarApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = FatJarApplication.class)
public abstract class AbstractApiIntegrationTest {

    @LocalServerPort
    private int port;

    protected ResponseEntity<ImportResultDto> postResource(final String path) {
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
        return restTemplate.postForEntity( getBaseUrl() + "/api/import", requestEntity, ImportResultDto.class);
    }

    protected ResponseEntity<ImportResultDto> importSitzplanFile()
    {
        return postResource("/imports/sitzplan.csv");
    }

    protected ResponseEntity<ImportResultDto> prepareSitzplanFile()
    {
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

    protected String getBaseUrl(){
        return "http://localhost:" + getPort();
    }

    protected void doTestInvalidMethod(String url){
        //Prepare
        final RestTemplate restTemplate = new RestTemplate();
        try{
            //Test
            restTemplate.postForEntity(getBaseUrl() + url, null, RoomDto.class, Collections.emptyMap());

            //Verify
            assertTrue(false); //Should never be called
        }catch (HttpClientErrorException e){
            assertEquals(HttpStatus.METHOD_NOT_ALLOWED, e.getStatusCode());
        }
    }
}
