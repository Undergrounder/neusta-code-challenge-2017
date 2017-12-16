package de.neustasd.roomsmanager.facades.imprt.converter.impl;

import de.neustasd.roomsmanager.core.services.converters.ConverterUtils;
import de.neustasd.roomsmanager.core.services.data.ImportData;
import de.neustasd.roomsmanager.core.services.data.RoomData;
import de.neustasd.roomsmanager.facades.imprt.converter.CsvImportDataConverter;
import de.neustasd.roomsmanager.facades.imprt.converter.CsvRoomDataConverter;
import de.neustasd.roomsmanager.facades.imprt.csv.parser.data.CsvImportData;
import de.neustasd.roomsmanager.facades.imprt.csv.parser.data.CsvRoomData;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** Created by Adrian Tello on 09/12/2017. */
@Component
public class CsvImportDataConverterImpl implements CsvImportDataConverter {

  private final CsvRoomDataConverter csvRoomDataConverter;

  @Autowired
  public CsvImportDataConverterImpl(final CsvRoomDataConverter csvRoomDataConverter) {
    this.csvRoomDataConverter = csvRoomDataConverter;
  }

  @Override
  public ImportData convert(final CsvImportData source) throws ConversionException {
    final List<CsvRoomData> sourceRoomDataList = source.getRoomDataList();
    final List<RoomData> roomDataList =
        ConverterUtils.convertAll(sourceRoomDataList, getCsvRoomDataConverter());

    return ImportData.builder().roomDataList(roomDataList).build();
  }

  private CsvRoomDataConverter getCsvRoomDataConverter() {
    return csvRoomDataConverter;
  }
}
