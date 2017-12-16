package de.neusta_sd.roomsmanager.facades.imprt.converter;

import de.neusta_sd.roomsmanager.core.services.ImportService;
import de.neusta_sd.roomsmanager.core.services.converters.Converter;
import de.neusta_sd.roomsmanager.facades.imprt.csv.parser.ImportCsvParser;

/** Created by Adrian Tello on 09/12/2017. */
public interface CsvImportDataConverter
    extends Converter<ImportCsvParser.CsvImportData, ImportService.ImportData> {}
