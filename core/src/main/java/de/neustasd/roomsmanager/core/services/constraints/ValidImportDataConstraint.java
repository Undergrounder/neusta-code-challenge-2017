package de.neustasd.roomsmanager.core.services.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Constraint for testing the validity of ImportData class.
 *
 * <p>Validates that no room number is repeated and that no person is in more than one room.
 *
 * @author Adrian Tello
 */
@Constraint(validatedBy = ValidImportDataConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ValidImportDataConstraint {
  /**
   * Get the default message.
   *
   * @return The message
   */
  String message() default "";

  /**
   * Get the validation groups
   *
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
