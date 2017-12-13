package de.neusta_sd.roomsmanager.core.services;

import de.neusta_sd.roomsmanager.core.services.constraints.RoomNumberConstraint;
import de.neusta_sd.roomsmanager.core.services.constraints.ValidImportDataConstraint;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import java.util.Collection;
import java.util.List;


/**
 * Created by Adrian Tello on 01/12/2017.
 */
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
        @NonNull
        @RoomNumberConstraint
        private String number;

        @Valid
        private List<PersonData> personDataList;
    }

    @Builder
    @Value
    class PersonData {
        private String title;

        @NonNull
        private String firstName;

        @NonNull
        private String lastName;

        private String nameAddition;

        @NonNull
        private String ldapUser;
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
