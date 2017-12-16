package de.neusta_sd.roomsmanager.core.entities.repositories;

import de.neusta_sd.roomsmanager.core.entities.Title;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/** Created by Adrian Tello on 29/11/2017. */
public interface TitleRepository extends JpaRepository<Title, Long> {

  Optional<Title> findOneByName(final String name);
}
