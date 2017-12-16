package de.neustasd.roomsmanager.core.services;

import de.neustasd.roomsmanager.core.services.constraints.RoomNumberConstraint;
import de.neustasd.roomsmanager.core.services.constraints.ValidImportDataConstraint;
import java.util.Collection;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;

/** Created by Adrian Tello on 01/12/2017. */
public interface ImportService {
  ImportResultData importData(final ImportData importData) throws ImportException;

  @Builder
  @Value
  @ValidImportDataConstraint
  class ImportData {
    @Singular("roomData")
    @NonNull
    @Valid
    private List<RoomData> roomDataList;
  }

  @Builder
  @Value
  class RoomData {
    @NonNull @RoomNumberConstraint private String number;

    @Valid private List<PersonData> personDataList;
  }

  @Builder
  @Value
  class PersonData {
    private String title;

    @NonNull private String firstName;

    @NonNull private String lastName;

    private String nameAddition;

    @NonNull private String ldapUser;
  }

  @Builder
  @Value
  class ImportResultData {
    private long persons;
    private long rooms;
  }

  class ImportException extends Exception {
    public ImportException(String message) {
      super(message);
    }

    public ImportException(String message, Throwable cause) {
      super(message, cause);
    }

    public ImportException(Throwable cause) {
      super(cause);
    }
  }

  class InvalidImportDataException extends ImportException {
    private final Collection<ConstraintViolation<ImportData>> constraintViolations;

    public InvalidImportDataException(
        final Collection<ConstraintViolation<ImportData>> constraintViolations) {
      super(buildMessage(constraintViolations));

      this.constraintViolations = constraintViolations;
    }

    private static String buildMessage(
        final Collection<ConstraintViolation<ImportData>> constraintViolations) {
      final StringBuilder messageBuilder = new StringBuilder();

      for (ConstraintViolation<ImportData> constraintViolation : constraintViolations) {
        messageBuilder.append(constraintViolation.toString());
      }

      return messageBuilder.toString();
    }

    public Collection<ConstraintViolation<ImportData>> getConstraintViolations() {
      return constraintViolations;
    }
  }
}
