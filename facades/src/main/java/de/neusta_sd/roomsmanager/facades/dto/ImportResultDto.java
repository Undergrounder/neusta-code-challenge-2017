package de.neusta_sd.roomsmanager.facades.dto;

import lombok.Builder;
import lombok.Value;

/**
 * Created by Adrian Tello on 11/12/2017.
 */
@Builder
@Value
public class ImportResultDto {
    private long persons;
    private long rooms;
}
