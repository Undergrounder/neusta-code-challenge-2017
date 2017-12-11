package de.neusta_sd.roomsmanager.fatjar.controllers;

import de.neusta_sd.roomsmanager.facades.dto.ImportResultDto;
import de.neusta_sd.roomsmanager.fatjar.FatJarApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

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
        return restTemplate.postForEntity("http://localhost:" + getPort() + "/api/import", requestEntity, ImportResultDto.class);
    }

    protected ResponseEntity<ImportResultDto> importSitzplanFile()
    {
        return postResource("/imports/sitzplan.csv");
    }

    protected int getPort() {
        return port;
    }
}
