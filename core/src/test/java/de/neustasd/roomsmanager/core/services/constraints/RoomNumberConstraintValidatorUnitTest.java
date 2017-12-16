package de.neustasd.roomsmanager.core.services.constraints;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import javax.validation.ConstraintValidatorContext;
import org.junit.Test;

/**
 * Unit-Test for the RoomNumberConstraintValidator.
 */
public class RoomNumberConstraintValidatorUnitTest {

  private final RoomNumberConstraintValidator roomNumberConstraintValidator = new RoomNumberConstraintValidator();

  /**
   * Tests if null is a valid value.
   */
  @Test
  public void testNull() {
    //Test
    final ConstraintValidatorContext constraintValidatorContext = mock(ConstraintValidatorContext.class);

    final boolean isValid = roomNumberConstraintValidator.isValid(null, constraintValidatorContext);

    //Verify
    assertTrue(isValid);
  }

  /**
   * Tests if null is a valid value.
   */
  @Test
  public void testValid() {
    //Test
    final ConstraintValidatorContext constraintValidatorContext = mock(ConstraintValidatorContext.class);

    final boolean isValid = roomNumberConstraintValidator.isValid("1111", constraintValidatorContext);

    //Verify
    assertTrue(isValid);
  }
}
