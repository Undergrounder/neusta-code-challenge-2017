package de.neustasd.roomsmanager.core.services.constraints;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import de.neustasd.roomsmanager.core.services.data.ImportData;
import de.neustasd.roomsmanager.core.services.data.PersonData;
import de.neustasd.roomsmanager.core.services.data.RoomData;
import javax.validation.ConstraintValidatorContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

/** Created by Adrian Tello on 16/12/2017. */
@RunWith(MockitoJUnitRunner.class)
public class ValidImportDataConstraintValidatorUnitTest {

  private final ValidImportDataConstraintValidator validImportDataConstraintValidator =
      new ValidImportDataConstraintValidator();

  @Test
  public void testEmpty() {
    // Prepare
    final ImportData importData = ImportData.builder().build();

    // Test
    final ConstraintValidatorContext constraintValidatorContext =
        mock(ConstraintValidatorContext.class);
    final boolean isValid =
        validImportDataConstraintValidator.isValid(importData, constraintValidatorContext);

    // Verify
    assertTrue(isValid);
  }

  @Test
  public void testMultiple() {
    // Prepare
    final PersonData mnolteData =
        PersonData.builder().firstName("Markus").lastName("Nolte").ldapUser("mnolte").build();

    final RoomData room1101Data = RoomData.builder().number("1101").personData(mnolteData).build();

    final PersonData cfleuterData =
        PersonData.builder().firstName("Claudia").lastName("Fleuter").ldapUser("cfleuter").build();

    final PersonData sstrodthoffData =
        PersonData.builder()
            .firstName("Sabine")
            .lastName("Strodthoff")
            .ldapUser("sstrodthoff")
            .build();

    final RoomData room1113Data =
        RoomData.builder()
            .number("1113")
            .personData(cfleuterData)
            .personData(sstrodthoffData)
            .build();

    final ImportData importData =
        ImportData.builder().roomData(room1101Data).roomData(room1113Data).build();

    // Test
    final ConstraintValidatorContext constraintValidatorContext =
        mock(ConstraintValidatorContext.class);
    final boolean isValid =
        validImportDataConstraintValidator.isValid(importData, constraintValidatorContext);

    // Verify
    assertTrue(isValid);
  }
}
