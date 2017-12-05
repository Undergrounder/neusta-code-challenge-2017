package de.neusta_sd.roomsmanager.frontend.controllers;

import de.neusta_sd.roomsmanager.frontend.controllers.exceptions.NotFoundException;
import de.neusta_sd.roomsmanager.frontend.dto.ExceptionDto;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Created by Adrian Tello on 04/12/2017.
 */
public abstract class AbstractApiController {
    private final static String NOT_FOUND_MESSAGE = "Not Found";

    @ExceptionHandler(value = {NoHandlerFoundException.class, NotFoundException.class})
    public ExceptionDto handleError404(Exception e) {
        return new ExceptionDto(404, NOT_FOUND_MESSAGE);
    }
}
