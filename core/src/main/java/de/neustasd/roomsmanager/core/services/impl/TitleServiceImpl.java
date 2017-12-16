package de.neustasd.roomsmanager.core.services.impl;

import de.neustasd.roomsmanager.core.entities.Title;
import de.neustasd.roomsmanager.core.entities.repositories.TitleRepository;
import de.neustasd.roomsmanager.core.services.TitleService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** Created by Adrian Tello on 09/12/2017. */
@Service
public class TitleServiceImpl implements TitleService {

  private final TitleRepository titleRepository;

  @Autowired
  public TitleServiceImpl(TitleRepository titleRepository) {
    this.titleRepository = titleRepository;
  }

  @Override
  public Optional<Title> getTitle(final String name) {
    return getTitleRepository().findOneByName(name);
  }

  @Override
  public boolean existsTitleByName(String name) {
    return this.getTitle(name).isPresent();
  }

  protected TitleRepository getTitleRepository() {
    return titleRepository;
  }
}
