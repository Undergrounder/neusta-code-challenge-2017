package de.neustasd.roomsmanager.frontend.controllers;

import de.neustasd.roomsmanager.frontend.controllers.exceptions.MethodNotAllowedException;
import de.neustasd.roomsmanager.frontend.controllers.exceptions.NotFoundException;
import de.neustasd.roomsmanager.frontend.dto.ExceptionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

/** Created by Adrian Tello on 04/12/2017. */
public abstract class AbstractApiController {

  private static final String NOT_FOUND_MESSAGE = "Not Found";
  private static final String METHOD_NOT_ALLOWED_MESSAGE = "Method not allowed";

  @ExceptionHandler(value = {Exception.class})
  public ResponseEntity<ExceptionDto> exceptionHandler(final Exception e) {
    if (e instanceof NoHandlerFoundException || e instanceof NotFoundException) {
      return createExceptionResponseEntity(404, NOT_FOUND_MESSAGE);
    } else if (e instanceof MethodNotAllowedException) {
      return createExceptionResponseEntity(405, METHOD_NOT_ALLOWED_MESSAGE);
    } else {
      return createExceptionResponseEntity(500, e.getMessage());
    }
  }

  protected ResponseEntity<ExceptionDto> createExceptionResponseEntity(
      int responseCode, String message) {
    return createExceptionResponseEntity(responseCode, responseCode, message);
  }

  protected ResponseEntity<ExceptionDto> createExceptionResponseEntity(
      int responseCode, int bodyCode, String message) {
    final ExceptionDto exceptionDto = new ExceptionDto(bodyCode, message);

    return ResponseEntity.status(responseCode).body(exceptionDto);
  }
}
