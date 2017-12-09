package de.neusta_sd.roomsmanager.facades.impl;

import de.neusta_sd.roomsmanager.facades.ImportFacade;
import de.neusta_sd.roomsmanager.facades.imprt.csv.parser.ImportCsvParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * Created by Adrian Tello on 09/12/2017.
 */
@Component
public class ImportFacadeImpl implements ImportFacade {

    private ImportCsvParser importCsvParser;

    @Autowired
    public ImportFacadeImpl(ImportCsvParser importCsvParser) {
        this.importCsvParser = importCsvParser;
    }

    @Override
    public void importStream(final InputStream inputStream) throws ImportException {
        final ImportCsvParser.CsvImportData csvImportData = parseCsv(inputStream);
        //TODO convert
        //TODO import
    }

    private ImportCsvParser.CsvImportData parseCsv(final InputStream inputStream) throws ImportException {
        try {
            return importCsvParser.parse(inputStream);
        } catch (ImportCsvParser.CsvParsingException e) {
            throw new ImportException("Invalid csv file", e);
        }
    }
}
