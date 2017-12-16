package de.neustasd.roomsmanager.core.services.data;

import de.neustasd.roomsmanager.core.services.ImportService;
import de.neustasd.roomsmanager.core.services.constraints.RoomNumberConstraint;
import java.util.List;
import javax.validation.Valid;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

/**
 * Created by Adrian Tello on 16/12/2017.
 */
@Builder
@Value
public class RoomData {
    @NonNull
    @RoomNumberConstraint
    private String number;

    @Valid
    private List<PersonData> personDataList;
}
