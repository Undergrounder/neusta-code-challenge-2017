package de.neusta_sd.roomsmanager.frontend.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Adrian Tello on 02/12/2017.
 */
@RestController
public class ApiController extends AbstractApiController {

    @RequestMapping("/api/**")
    public ResponseEntity<Object> unhandledCall(final HttpServletRequest request, @RequestHeader final HttpHeaders httpHeaders) throws NoHandlerFoundException {
        final String method = request.getMethod();
        final String requestUri = request.getRequestURI();

        throw new NoHandlerFoundException(method, requestUri, httpHeaders);
    }
}
