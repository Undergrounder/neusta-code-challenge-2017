package de.neustasd.roomsmanager.facades.imprt.csv.parser.impl;

import de.neustasd.roomsmanager.core.services.converters.Converter;
import de.neustasd.roomsmanager.facades.imprt.csv.parser.ImportCsvParser;
import de.neustasd.roomsmanager.facades.imprt.csv.parser.converter.PersonStringConverter;
import de.neustasd.roomsmanager.facades.imprt.csv.parser.data.CsvImportData;
import de.neustasd.roomsmanager.facades.imprt.csv.parser.data.CsvPersonData;
import de.neustasd.roomsmanager.facades.imprt.csv.parser.data.CsvRoomData;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/** Created by Adrian Tello on 09/12/2017. */
@Component
public class ImportCsvParserImpl implements ImportCsvParser {

  private static final String INPUT_STREAM_ENCODING = "UTF-8";

  private final PersonStringConverter personStringConverter;

  @Autowired
  public ImportCsvParserImpl(PersonStringConverter personStringConverter) {
    this.personStringConverter = personStringConverter;
  }

  @Override
  public CsvImportData parse(final InputStream inputStream)
      throws CsvParsingException, IOException {
    try {
      final List<CsvRoomData> roomDataList = doParse(inputStream);

      return CsvImportData.builder().roomDataList(roomDataList).build();
    } catch (final Converter.ConversionException e) {
      throw new CsvParsingException(e.getMessage(), e);
    }
  }

  private List<CsvRoomData> doParse(final InputStream inputStream) throws IOException {
    final Reader reader = new InputStreamReader(inputStream, INPUT_STREAM_ENCODING);
    final CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT);

    final List<CsvRoomData> roomDataList = new ArrayList<>();
    for (CSVRecord csvRecord : parser) {
      final CsvRoomData csvRoomData = convertCsvRecord(csvRecord);
      roomDataList.add(csvRoomData);
    }

    return roomDataList;
  }

  private CsvRoomData convertCsvRecord(final CSVRecord csvRecord) {
    final Iterator<String> csvRecordIt = csvRecord.iterator();

    final String number = csvRecordIt.next();

    final List<CsvPersonData> personDataList = new ArrayList<>();
    while (csvRecordIt.hasNext()) {
      final String personCellStr = csvRecordIt.next();

      if (!StringUtils.isEmpty(personCellStr)) {
        final CsvPersonData csvPersonData = getPersonStringConverter().convert(personCellStr);
        personDataList.add(csvPersonData);
      }
    }
    return CsvRoomData.builder().number(number).personDataList(personDataList).build();
  }

  protected PersonStringConverter getPersonStringConverter() {
    return personStringConverter;
  }
}
