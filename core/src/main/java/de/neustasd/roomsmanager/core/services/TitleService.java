package de.neustasd.roomsmanager.core.services;

import de.neustasd.roomsmanager.core.entities.Title;
import java.util.Optional;

/** Created by Adrian Tello on 09/12/2017. */
public interface TitleService {
  Optional<Title> getTitle(final String name);

  boolean existsTitleByName(final String name);
}
