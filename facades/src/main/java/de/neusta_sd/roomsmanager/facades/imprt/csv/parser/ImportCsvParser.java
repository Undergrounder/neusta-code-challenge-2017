package de.neusta_sd.roomsmanager.facades.imprt.csv.parser;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Adrian Tello on 09/12/2017.
 */
public interface ImportCsvParser {
    CsvImportData parse(final InputStream inputStream) throws CsvParsingException;

    @Builder
    @Value
    class CsvImportData {
        private List<CsvRoomData> roomDataList;
    }

    @Builder
    @Value
    class CsvRoomData {
        @NonNull
        private String number;
        private List<CsvPersonData> personDataList;
    }

    @Builder
    @Value
    class CsvPersonData {
        private String title;
        private String firstName;
        private String lastName;
        private String nameAddition;
        @NonNull
        private String ldapUser;
    }

    class CsvParsingException extends Exception {
        public CsvParsingException(final Throwable cause) {
            super(cause);
        }
    }
}
