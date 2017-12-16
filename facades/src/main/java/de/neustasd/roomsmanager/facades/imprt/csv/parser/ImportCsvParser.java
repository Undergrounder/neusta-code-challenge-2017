package de.neustasd.roomsmanager.facades.imprt.csv.parser;

import de.neustasd.roomsmanager.facades.imprt.csv.parser.data.CsvImportData;
import java.io.InputStream;

/** Created by Adrian Tello on 09/12/2017. */
public interface ImportCsvParser {
    CsvImportData parse(final InputStream inputStream) throws CsvParsingException;

    class CsvParsingException extends Exception {
        public CsvParsingException(final Throwable cause) {
            super(cause);
        }
    }
}
