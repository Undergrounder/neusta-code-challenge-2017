package de.neustasd.roomsmanager.core.services.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/** Created by Adrian Tello on 13/12/2017. */
public class RoomNumberConstraintValidator
    implements ConstraintValidator<RoomNumberConstraint, String> {

  @Override
  public void initialize(RoomNumberConstraint constraintAnnotation) {}

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return value == null || hasValidFormat(value);
  }

  private boolean hasValidFormat(String value) {
    return value.length() == 4;
  }
}
