package de.neusta_sd.roomsmanager.facades.dto;

import lombok.NonNull;
import lombok.Value;

import java.util.List;

/**
 * Created by Adrian Tello on 05/12/2017.
 */
@Value
public class RoomDto {
    @NonNull
    private String room;

    @NonNull
    private List<PersonDto> people;
}
