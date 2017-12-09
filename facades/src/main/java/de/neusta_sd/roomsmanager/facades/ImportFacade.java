package de.neusta_sd.roomsmanager.facades;

import lombok.Builder;
import lombok.Value;

import java.io.InputStream;

/**
 * Created by Adrian Tello on 09/12/2017.
 */
public interface ImportFacade {
    PostImportResultData importStream(final InputStream inputStream) throws ImportException;

    @Builder
    @Value
    class PostImportResultData {

    }

    class ImportException extends Exception {
        public ImportException(final String message, final Throwable cause) {
            super(message, cause);
        }
    }
}
