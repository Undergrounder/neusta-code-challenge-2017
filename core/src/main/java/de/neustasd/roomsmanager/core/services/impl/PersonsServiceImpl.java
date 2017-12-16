package de.neustasd.roomsmanager.core.services.impl;

import de.neustasd.roomsmanager.core.entities.Person;
import de.neustasd.roomsmanager.core.entities.repositories.PersonRepository;
import de.neustasd.roomsmanager.core.services.PersonsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** Created by Adrian Tello on 09/12/2017. */
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
