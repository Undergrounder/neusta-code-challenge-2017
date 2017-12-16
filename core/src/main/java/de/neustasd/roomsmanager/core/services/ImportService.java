package de.neustasd.roomsmanager.core.services;

import de.neustasd.roomsmanager.core.services.data.ImportData;
import de.neustasd.roomsmanager.core.services.data.ImportResultData;
import java.util.Collection;
import javax.validation.ConstraintViolation;

/** Created by Adrian Tello on 01/12/2017. */
public interface ImportService {
  ImportResultData importData(final ImportData importData) throws ImportException;

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
