package de.neusta_sd.roomsmanager.core.services;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import javax.validation.ConstraintViolation;
import java.util.Collection;
import java.util.List;


/**
 * Created by Adrian Tello on 01/12/2017.
 */
public interface ImportService {
    void importData(final ImportData importData) throws ImportException;

    @Builder
    @Value
    class ImportData {
        private List<RoomData> roomDataList;
    }

    @Builder
    @Value
    class RoomData {
        @NonNull
        private String number;
        private List<PersonData> personDataList;
    }

    @Builder
    @Value
    class PersonData {
        private String title;
        private String firstName;
        private String lastName;
        private String nameAddition;
        @NonNull
        private String ldapUser;
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
        public InvalidImportDataException(final Collection<ConstraintViolation<ImportData>> constraintViolations) {
            super(buildMessage(constraintViolations));
        }

        private static String buildMessage(final Collection<ConstraintViolation<ImportData>> constraintViolations) {
            final StringBuilder messageBuilder = new StringBuilder();

            for (ConstraintViolation<ImportData> constraintViolation : constraintViolations) {
                messageBuilder.append(constraintViolation.toString());
            }

            return messageBuilder.toString();
        }
    }
}