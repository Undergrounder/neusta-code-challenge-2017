package de.neustasd.roomsmanager.facades.imprt.converter.impl;

import de.neustasd.roomsmanager.core.services.ImportService;
import de.neustasd.roomsmanager.core.services.converters.ConverterUtils;
import de.neustasd.roomsmanager.facades.imprt.converter.CsvPersonDataConverter;
import de.neustasd.roomsmanager.facades.imprt.converter.CsvRoomDataConverter;
import de.neustasd.roomsmanager.facades.imprt.csv.parser.ImportCsvParser;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** Created by Adrian Tello on 09/12/2017. */
@Component
public class CsvRoomDataConverterImpl implements CsvRoomDataConverter {

  private CsvPersonDataConverter csvPersonDataConverter;

  @Autowired
  public CsvRoomDataConverterImpl(final CsvPersonDataConverter csvPersonDataConverter) {
    this.csvPersonDataConverter = csvPersonDataConverter;
  }

  @Override
  public ImportService.RoomData convert(final ImportCsvParser.CsvRoomData source)
      throws ConversionException {
    // Get values
    final String sourceNumber = source.getNumber();
    final List<ImportCsvParser.CsvPersonData> sourcePersonDataList = source.getPersonDataList();

    // Convert
    List<ImportService.PersonData> targetPersonDataList =
        ConverterUtils.convertAll(sourcePersonDataList, getCsvPersonDataConverter());

    // Create object
    return ImportService.RoomData.builder()
        .number(sourceNumber)
        .personDataList(targetPersonDataList)
        .build();
  }

  private CsvPersonDataConverter getCsvPersonDataConverter() {
    return csvPersonDataConverter;
  }
}
