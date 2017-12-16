package de.neustasd.roomsmanager.core.entities.repositories;

import de.neustasd.roomsmanager.core.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for the Person entity.
 *
 * <p>Allows searching and storing Person entities.
 *
 * @author Adrian Tello
 */
public interface PersonRepository extends JpaRepository<Person, Long> {}
