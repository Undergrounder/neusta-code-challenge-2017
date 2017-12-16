package de.neustasd.roomsmanager.core.entities.repositories;

import de.neustasd.roomsmanager.core.entities.NameAddition;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for the NameAddition entity.
 *
 * <P>Allows searching and storing NameAddition entities.</P>
 *
 * @author Adrian Tello
 */
public interface NameAdditionRepository extends JpaRepository<NameAddition, Long> {

  Optional<NameAddition> findOneByName(final String name);
}
