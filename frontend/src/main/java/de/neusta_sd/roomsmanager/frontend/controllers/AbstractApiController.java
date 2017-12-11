package de.neusta_sd.roomsmanager.frontend.controllers;

import de.neusta_sd.roomsmanager.frontend.controllers.exceptions.NotFoundException;
import de.neusta_sd.roomsmanager.frontend.dto.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Created by Adrian Tello on 04/12/2017.
 */
public abstract class AbstractApiController {
    private final static String NOT_FOUND_MESSAGE = "Not Found";

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ExceptionDto> exceptionHandler(final Exception e) {
        final ExceptionDto exceptionDto = handleException(e);

        return ResponseEntity
                .status(exceptionDto.getCode())
                .body(exceptionDto);
    }

    private ExceptionDto handleException(final Exception e){
        if (e instanceof NoHandlerFoundException || e instanceof NotFoundException) {
            return new ExceptionDto(404, NOT_FOUND_MESSAGE);
        } else {
            return new ExceptionDto(500, e.getMessage());
        }
    }
}
