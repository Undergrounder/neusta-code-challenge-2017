package de.neusta_sd.roomsmanager.facades.impl;

import de.neusta_sd.roomsmanager.core.services.ImportService;
import de.neusta_sd.roomsmanager.facades.ImportFacade;
import de.neusta_sd.roomsmanager.facades.converters.ImportResultDataConverter;
import de.neusta_sd.roomsmanager.facades.dto.ImportResultDto;
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

    private ImportResultDataConverter importResultDataConverter;

    @Autowired
    public ImportFacadeImpl(ImportCsvParser importCsvParser, CsvImportDataConverter csvImportDataConverter, ImportService importService, ImportResultDataConverter importResultDataConverter) {
        this.importCsvParser = importCsvParser;
        this.csvImportDataConverter = csvImportDataConverter;
        this.importService = importService;
        this.importResultDataConverter = importResultDataConverter;
    }

    @Override
    public ImportResultDto importStream(final InputStream inputStream) throws ImportException {
        final ImportCsvParser.CsvImportData csvImportData = parseCsv(inputStream);
        final ImportService.ImportData importData = getCsvImportDataConverter().convert(csvImportData);

        final ImportService.ImportResultData importResultData = doImport(importData);

        return getImportResultDataConverter().convert(importResultData);
    }

    private ImportCsvParser.CsvImportData parseCsv(final InputStream inputStream) throws ImportException {
        try {
            return getImportCsvParser().parse(inputStream);
        } catch (ImportCsvParser.CsvParsingException e) {
            throw new ImportException("Invalid csv file", e);
        }
    }

    private ImportService.ImportResultData doImport(ImportService.ImportData importData) throws ImportException {
        try {
            return getImportService().importData(importData);
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

    private ImportResultDataConverter getImportResultDataConverter() {
        return importResultDataConverter;
    }
}
