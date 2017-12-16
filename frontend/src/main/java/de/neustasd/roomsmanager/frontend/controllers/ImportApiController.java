package de.neustasd.roomsmanager.frontend.controllers;

import static de.neustasd.roomsmanager.frontend.controllers.ImportApiController.BASE_PATH;

import de.neustasd.roomsmanager.facades.ImportFacade;
import de.neustasd.roomsmanager.facades.dto.ImportResultDto;
import de.neustasd.roomsmanager.frontend.controllers.exceptions.MethodNotAllowedException;
import de.neustasd.roomsmanager.frontend.dto.ExceptionDto;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/** Created by Adrian Tello on 09/12/2017. */
@RestController
@RequestMapping(BASE_PATH)
public class ImportApiController extends AbstractApiController {

  public static final String BASE_PATH = ApiController.BASE_PATH + "/import";

  private final ImportFacade importFacade;

  public ImportApiController(final ImportFacade importFacade) {
    this.importFacade = importFacade;
  }

  @RequestMapping(
    consumes = "multipart/form-data",
    method = RequestMethod.POST,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  @ResponseStatus(HttpStatus.ACCEPTED)
  public ImportResultDto postImport(@RequestParam("file") MultipartFile file)
      throws IOException, ImportFacade.ImportException {
    try (InputStream fileInputStream = file.getInputStream()) {
      return getImportFacade().importStream(fileInputStream);
    }
  }

  @RequestMapping
  public void othersImport() throws MethodNotAllowedException {
    throw new MethodNotAllowedException();
  }

  @ExceptionHandler(value = {ImportFacade.ImportValidationFailedException.class})
  public ResponseEntity<ExceptionDto> importValidationFailedHandler(
      final ImportFacade.ImportValidationFailedException e) {

    final int bodyCode = getBodyCodeForException(e);
    return createExceptionResponseEntity(400, bodyCode, e.getMessage());
  }

  private int getBodyCodeForException(ImportFacade.ImportValidationFailedException e) {
    int code = 400;
    switch (e.getFailedValidation()) {
      case DUPLICATED_ROOM_NUMBER:
        code = 2;
        break;
      case DUPLICATED_PERSON:
        code = 3;
        break;
      case INVALID_ENTRY:
        code = 4;
        break;
      default:
        // code = 400
    }

    return code;
  }

  private ImportFacade getImportFacade() {
    return importFacade;
  }
}
