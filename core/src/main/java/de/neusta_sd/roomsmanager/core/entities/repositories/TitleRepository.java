package de.neusta_sd.roomsmanager.core.entities.repositories;

import de.neusta_sd.roomsmanager.core.entities.Title;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by Adrian Tello on 29/11/2017.
 */
public interface TitleRepository extends CrudRepository<Title, Long> {

    Optional<Title> findOneByName(final String name);
}
