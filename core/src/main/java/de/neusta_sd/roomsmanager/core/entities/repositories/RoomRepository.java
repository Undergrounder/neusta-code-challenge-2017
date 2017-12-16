package de.neusta_sd.roomsmanager.core.entities.repositories;

import de.neusta_sd.roomsmanager.core.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/** Created by Adrian Tello on 29/11/2017. */
public interface RoomRepository extends JpaRepository<Room, Long> {
  Optional<Room> findOneByNumber(final String number);
}
