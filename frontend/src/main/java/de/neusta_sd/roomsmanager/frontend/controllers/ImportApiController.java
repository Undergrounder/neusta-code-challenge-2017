package de.neusta_sd.roomsmanager.frontend.controllers;

import de.neusta_sd.roomsmanager.facades.ImportFacade;
import de.neusta_sd.roomsmanager.facades.dto.ImportResultDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

import static de.neusta_sd.roomsmanager.frontend.controllers.ImportApiController.BASE_PATH;

/**
 * Created by Adrian Tello on 09/12/2017.
 */
@RestController
@RequestMapping(BASE_PATH)
public class ImportApiController extends AbstractApiController {

    public static final String BASE_PATH = ApiController.BASE_PATH + "/import";

    private final ImportFacade importFacade;

    public ImportApiController(final ImportFacade importFacade) {
        this.importFacade = importFacade;
    }

    @RequestMapping(consumes = "multipart/form-data", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ImportResultDto postImport(@RequestParam("file") MultipartFile file) throws IOException, ImportFacade.ImportException {
        try (InputStream fileInputStream = file.getInputStream()) {
            return getImportFacade().importStream(fileInputStream);
        }
    }

    private ImportFacade getImportFacade() {
        return importFacade;
    }
}
