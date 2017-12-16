package de.neustasd.roomsmanager.facades.imprt.csv.parser;

import java.io.InputStream;
import java.util.List;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;

/** Created by Adrian Tello on 09/12/2017. */
public interface ImportCsvParser {
  CsvImportData parse(final InputStream inputStream) throws CsvParsingException;

  @Builder
  @Value
  class CsvImportData {
    @Singular("roomData")
    private List<CsvRoomData> roomDataList;
  }

  @Builder
  @Value
  class CsvRoomData {
    @NonNull private String number;

    @Singular("personData")
    private List<CsvPersonData> personDataList;
  }

  @Builder
  @Value
  class CsvPersonData {
    private String title;
    private String firstName;
    private String lastName;
    private String nameAddition;
    @NonNull private String ldapUser;
  }

  class CsvParsingException extends Exception {
    public CsvParsingException(final Throwable cause) {
      super(cause);
    }
  }
}
