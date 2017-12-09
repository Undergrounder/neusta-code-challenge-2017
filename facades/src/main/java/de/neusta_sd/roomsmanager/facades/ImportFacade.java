package de.neusta_sd.roomsmanager.facades;

import java.io.InputStream;

/**
 * Created by Adrian Tello on 09/12/2017.
 */
public interface ImportFacade {
    void importStream(final InputStream inputStream) throws ImportException;

    class ImportException extends Exception {
        public ImportException(final String message, final Throwable cause) {
            super(message, cause);
        }
    }
}
