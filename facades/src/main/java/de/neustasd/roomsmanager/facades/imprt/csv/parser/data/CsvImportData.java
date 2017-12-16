package de.neustasd.roomsmanager.facades.imprt.csv.parser.data;

import java.util.List;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

/** Created by Adrian Tello on 16/12/2017. */
@Builder
@Value
public class CsvImportData {
  @Singular("roomData")
  private List<CsvRoomData> roomDataList;
}
