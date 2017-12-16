package de.neustasd.roomsmanager.core.entities.repositories;

import de.neustasd.roomsmanager.core.entities.Title;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for the Title entity.
 *
 * <P>Allows searching and storing Title entities.</P>
 *
 * @author Adrian Tello
 */
public interface TitleRepository extends JpaRepository<Title, Long> {

  /**
   * Finds one Title by its name.
   *
   * @param name Name to search for
   *
   * @return Nullable optional if no title found or an optional with the title if found.
   */
  Optional<Title> findOneByName(final String name);
}
