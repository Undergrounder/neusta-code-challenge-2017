package de.neusta_sd.roomsmanager.facades.impl;

import de.neusta_sd.roomsmanager.core.services.ImportService;
import de.neusta_sd.roomsmanager.facades.ImportFacade;
import de.neusta_sd.roomsmanager.facades.imprt.converter.CsvImportDataConverter;
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

    private CsvImportDataConverter csvImportDataConverter;

    private ImportService importService;

    @Autowired
    public ImportFacadeImpl(ImportCsvParser importCsvParser, CsvImportDataConverter csvImportDataConverter, ImportService importService) {
        this.importCsvParser = importCsvParser;
        this.csvImportDataConverter = csvImportDataConverter;
        this.importService = importService;
    }

    @Override
    public void importStream(final InputStream inputStream) throws ImportException {
        final ImportCsvParser.CsvImportData csvImportData = parseCsv(inputStream);
        final ImportService.ImportData importData = getCsvImportDataConverter().convert(csvImportData);
        doImport(importData);
    }

    private ImportCsvParser.CsvImportData parseCsv(final InputStream inputStream) throws ImportException {
        try {
            return getImportCsvParser().parse(inputStream);
        } catch (ImportCsvParser.CsvParsingException e) {
            throw new ImportException("Invalid csv file", e);
        }
    }

    private void doImport(ImportService.ImportData importData) throws ImportException {
        try {
            getImportService().importData(importData);
        } catch (ImportService.ImportException e) {
            throw new ImportException("Error happened while importing data", e);
        }
    }

    private ImportCsvParser getImportCsvParser() {
        return importCsvParser;
    }

    private CsvImportDataConverter getCsvImportDataConverter() {
        return csvImportDataConverter;
    }

    private ImportService getImportService() {
        return importService;
    }
}
