package de.neusta_sd.roomsmanager.facades.dto;

import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;

import java.util.List;

/** Created by Adrian Tello on 11/12/2017. */
@Builder
@Value
public class RoomsDto {

  @NonNull @Singular private List<RoomDto> rooms;
}
