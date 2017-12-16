package de.neustasd.roomsmanager.frontend.controllers;

import static de.neustasd.roomsmanager.frontend.controllers.ApiController.BASE_PATH;

import de.neustasd.roomsmanager.frontend.controllers.exceptions.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Created by Adrian Tello on 02/12/2017. */
@RestController
@RequestMapping(BASE_PATH)
public class ApiController extends AbstractApiController {

  public static final String BASE_PATH = "/api";

  @RequestMapping("/**")
  public ResponseEntity<Object> unhandledCall() throws NotFoundException {
    throw new NotFoundException("Not Found.");
  }
}
