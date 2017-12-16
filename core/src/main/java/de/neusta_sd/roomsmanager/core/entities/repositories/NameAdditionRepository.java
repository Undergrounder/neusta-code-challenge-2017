package de.neusta_sd.roomsmanager.core.entities.repositories;

import de.neusta_sd.roomsmanager.core.entities.NameAddition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/** Created by Adrian Tello on 29/11/2017. */
public interface NameAdditionRepository extends JpaRepository<NameAddition, Long> {

  Optional<NameAddition> findOneByName(final String name);
}
