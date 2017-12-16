package de.neustasd.roomsmanager.facades.imprt.converter;

import de.neustasd.roomsmanager.core.services.converters.Converter;
import de.neustasd.roomsmanager.core.services.data.PersonData;
import de.neustasd.roomsmanager.facades.imprt.csv.parser.ImportCsvParser;

/** Created by Adrian Tello on 09/12/2017. */
public interface CsvPersonDataConverter
    extends Converter<ImportCsvParser.CsvPersonData, PersonData> {}
