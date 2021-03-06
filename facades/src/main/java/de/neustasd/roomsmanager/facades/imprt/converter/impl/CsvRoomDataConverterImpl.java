package de.neustasd.roomsmanager.facades.imprt.converter.impl;

import de.neustasd.roomsmanager.core.services.converters.ConverterUtils;
import de.neustasd.roomsmanager.core.services.data.PersonData;
import de.neustasd.roomsmanager.core.services.data.RoomData;
import de.neustasd.roomsmanager.facades.imprt.converter.CsvPersonDataConverter;
import de.neustasd.roomsmanager.facades.imprt.converter.CsvRoomDataConverter;
import de.neustasd.roomsmanager.facades.imprt.csv.parser.data.CsvPersonData;
import de.neustasd.roomsmanager.facades.imprt.csv.parser.data.CsvRoomData;
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
  public RoomData convert(final CsvRoomData source) throws ConversionException {
    // Get values
    final String sourceNumber = source.getNumber();
    final List<CsvPersonData> sourcePersonDataList = source.getPersonDataList();

    // Convert
    List<PersonData> targetPersonDataList =
        ConverterUtils.convertAll(sourcePersonDataList, getCsvPersonDataConverter());

    // Create object
    return RoomData.builder().number(sourceNumber).personDataList(targetPersonDataList).build();
  }

  private CsvPersonDataConverter getCsvPersonDataConverter() {
    return csvPersonDataConverter;
  }
}
