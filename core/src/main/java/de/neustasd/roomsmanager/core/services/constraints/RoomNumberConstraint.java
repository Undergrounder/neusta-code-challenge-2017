package de.neustasd.roomsmanager.core.services.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Constraint for validating the room number.
 *
 * @author Adrian Tello
 */
@Constraint(validatedBy = RoomNumberConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface RoomNumberConstraint {
  /**
   * Get the default message.
   *
   * @return The message
   */
  String message() default "";

  /**
   *  Get the validation groups
   * @return The validation groups
   */
  Class<?>[] groups() default {};

  /**
   * Get the payloads
   *
   * @return The payloads
   */
  Class<? extends Payload>[] payload() default {};
}
