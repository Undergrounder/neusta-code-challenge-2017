package de.neustasd.roomsmanager.facades.dto;

import java.util.List;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;

/**
 * Room data transfer object.
 *
 * <p>Represents a room and its people.
 *
 * @author Adrian Tello
 */
@Builder
@Value
public class RoomDto {
  /** Room number */
  @NonNull private String room;

  /** People in the room */
  @NonNull
  @Singular("person")
  private List<PersonDto> people;
}
