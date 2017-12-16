package de.neustasd.roomsmanager.core.services.converters.impl;

import de.neustasd.roomsmanager.core.entities.Title;
import de.neustasd.roomsmanager.core.entities.repositories.TitleRepository;
import de.neustasd.roomsmanager.core.services.converters.TitleNameConverter;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** Created by Adrian Tello on 01/12/2017. */
@Component
public class TitleNameConverterImpl implements TitleNameConverter {

  @Autowired private TitleRepository titleRepository;

  @Override
  public Title convert(String source) throws ConversionException {
    final Optional<Title> titleOptional = titleRepository.findOneByName(source);
    if (!titleOptional.isPresent()) {
      throw new ConversionException("No title entity found for [" + source + "]");
    }

    return titleOptional.get();
  }
}
