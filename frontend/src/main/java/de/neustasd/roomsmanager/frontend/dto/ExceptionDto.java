package de.neustasd.roomsmanager.frontend.dto;

import lombok.Value;

/** Created by Adrian Tello on 04/12/2017. */
@Value
public class ExceptionDto {
  private int code;

  private String message;
}
