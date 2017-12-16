package de.neusta_sd.roomsmanager.facades.imprt.converter.impl;

import de.neusta_sd.roomsmanager.core.services.ImportService;
import de.neusta_sd.roomsmanager.core.services.converters.ConverterUtils;
import de.neusta_sd.roomsmanager.facades.imprt.converter.CsvImportDataConverter;
import de.neusta_sd.roomsmanager.facades.imprt.converter.CsvRoomDataConverter;
import de.neusta_sd.roomsmanager.facades.imprt.csv.parser.ImportCsvParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/** Created by Adrian Tello on 09/12/2017. */
@Component
public class CsvImportDataConverterImpl implements CsvImportDataConverter {

  private final CsvRoomDataConverter csvRoomDataConverter;

  @Autowired
  public CsvImportDataConverterImpl(final CsvRoomDataConverter csvRoomDataConverter) {
    this.csvRoomDataConverter = csvRoomDataConverter;
  }

  @Override
  public ImportService.ImportData convert(final ImportCsvParser.CsvImportData source)
      throws ConversionException {
    final List<ImportCsvParser.CsvRoomData> sourceRoomDataList = source.getRoomDataList();
    final List<ImportService.RoomData> roomDataList =
        ConverterUtils.convertAll(sourceRoomDataList, getCsvRoomDataConverter());

    return ImportService.ImportData.builder().roomDataList(roomDataList).build();
  }

  private CsvRoomDataConverter getCsvRoomDataConverter() {
    return csvRoomDataConverter;
  }
}
