package de.neustasd.roomsmanager.core.services.data;

import de.neustasd.roomsmanager.core.services.constraints.ValidImportDataConstraint;
import java.util.List;
import javax.validation.Valid;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;

/** Created by Adrian Tello on 16/12/2017. */
@Builder
@Value
@ValidImportDataConstraint
public class ImportData {
  @Singular("roomData")
  @NonNull
  @Valid
  private List<RoomData> roomDataList;
}
