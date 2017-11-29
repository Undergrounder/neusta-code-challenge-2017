package de.neusta_sd.roomsmanager.core.entities.repositories;

import de.neusta_sd.roomsmanager.core.entities.Person;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Adrian Tello on 29/11/2017.
 */
public interface PersonRepository extends CrudRepository<Person, Long> {
}
