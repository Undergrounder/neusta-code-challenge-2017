package de.neustasd.roomsmanager.core.entities.repositories;

import de.neustasd.roomsmanager.core.entities.Room;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for the Room entity.
 *
 * <P>Allows searching and storing Room entities.</P>
 *
 * @author Adrian Tello
 */
public interface RoomRepository extends JpaRepository<Room, Long> {
  Optional<Room> findOneByNumber(final String number);
}
