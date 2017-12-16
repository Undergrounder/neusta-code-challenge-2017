package de.neustasd.roomsmanager.core.services.data;

import lombok.Builder;
import lombok.Value;

/**
 * Created by Adrian Tello on 16/12/2017.
 */
@Builder
@Value
public class ImportResultData {
    private long persons;
    private long rooms;
}
