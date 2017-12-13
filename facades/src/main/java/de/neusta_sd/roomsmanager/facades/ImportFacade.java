package de.neusta_sd.roomsmanager.facades;

import de.neusta_sd.roomsmanager.facades.dto.ImportResultDto;
import lombok.Builder;
import lombok.Value;

import java.io.InputStream;

/**
 * Created by Adrian Tello on 09/12/2017.
 */
public interface ImportFacade {
    ImportResultDto importStream(final InputStream inputStream) throws ImportException;

    class ImportException extends Exception {
        public ImportException(final String message, final Throwable cause) {
            super(message, cause);
        }
    }

    class ImportValidationFailedException extends ImportException{
        public ImportValidationFailedException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
