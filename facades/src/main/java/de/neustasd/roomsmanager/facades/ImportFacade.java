package de.neustasd.roomsmanager.facades;

import de.neustasd.roomsmanager.facades.dto.ImportResultDto;

import java.io.IOException;
import java.io.InputStream;

/**
 * Facade for the import.
 *
 * <p>Allows importing rooms data.</p>
 *
 *  @author Adrian Tello
 */
public interface ImportFacade {
  ImportResultDto importStream(final InputStream inputStream) throws ImportException, IOException;

  class ImportException extends Exception {
    public ImportException(final String message, final Throwable cause) {
      super(message, cause);
    }
  }

  class ImportValidationFailedException extends ImportException {
    private final FailedValidation failedValidation;

    public ImportValidationFailedException(
        String message, Throwable cause, final FailedValidation failedValidation) {
      super(message, cause);
      this.failedValidation = failedValidation;
    }

    public FailedValidation getFailedValidation() {
      return failedValidation;
    }

    public enum FailedValidation {
      DUPLICATED_ROOM_NUMBER,
      DUPLICATED_PERSON,
      INVALID_ENTRY,
      INVALID_FILE,
      OTHER;
    }
  }
}
