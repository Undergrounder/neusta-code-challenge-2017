package de.neustasd.roomsmanager.facades.imprt.converter;

import de.neustasd.roomsmanager.core.services.converters.Converter;
import de.neustasd.roomsmanager.core.services.data.ImportData;
import de.neustasd.roomsmanager.facades.imprt.csv.parser.data.CsvImportData;

/** Created by Adrian Tello on 09/12/2017. */
public interface CsvImportDataConverter extends Converter<CsvImportData, ImportData> {}
