package de.neustasd.roomsmanager.core.services.impl;

import de.neustasd.roomsmanager.core.entities.NameAddition;
import de.neustasd.roomsmanager.core.entities.repositories.NameAdditionRepository;
import de.neustasd.roomsmanager.core.services.NameAdditionService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** Created by Adrian Tello on 09/12/2017. */
@Service
public class NameAdditionServiceImpl implements NameAdditionService {

  private NameAdditionRepository nameAdditionRepository;

  @Autowired
  public NameAdditionServiceImpl(NameAdditionRepository nameAdditionRepository) {
    this.nameAdditionRepository = nameAdditionRepository;
  }

  @Override
  public Optional<NameAddition> getNameAddition(String name) {
    return getNameAdditionRepository().findOneByName(name);
  }

  @Override
  public boolean existsNameAddition(String name) {
    return getNameAddition(name).isPresent();
  }

  protected NameAdditionRepository getNameAdditionRepository() {
    return nameAdditionRepository;
  }
}
