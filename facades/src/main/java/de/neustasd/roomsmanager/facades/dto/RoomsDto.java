package de.neustasd.roomsmanager.facades.dto;

import java.util.List;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;

/** Created by Adrian Tello on 11/12/2017. */
@Builder
@Value
public class RoomsDto {

  @NonNull @Singular private List<RoomDto> rooms;
}
