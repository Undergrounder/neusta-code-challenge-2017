package de.neusta_sd.roomsmanager.core.services.impl;

import de.neusta_sd.roomsmanager.core.entities.Person;
import de.neusta_sd.roomsmanager.core.entities.repositories.PersonRepository;
import de.neusta_sd.roomsmanager.core.services.PersonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Adrian Tello on 09/12/2017.
 */
@Service
public class PersonsServiceImpl implements PersonsService {

    private PersonRepository personRepository;

    @Autowired
    public PersonsServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> findAll() {
        return getPersonRepository().findAll();
    }

    private PersonRepository getPersonRepository() {
        return personRepository;
    }
}
