package de.neustasd.roomsmanager.facades.converters.impl;

import de.neustasd.roomsmanager.core.entities.NameAddition;
import de.neustasd.roomsmanager.core.entities.Person;
import de.neustasd.roomsmanager.core.entities.Title;
import de.neustasd.roomsmanager.facades.converters.PersonConverter;
import de.neustasd.roomsmanager.facades.dto.PersonDto;
import org.springframework.stereotype.Component;

/** Created by Adrian Tello on 05/12/2017. */
@Component
public class PersonConverterImpl implements PersonConverter {
  @Override
  public PersonDto convert(final Person source) throws ConversionException {
    final PersonDto.PersonDtoBuilder builder =
        PersonDto.builder()
            .firstName(source.getFirstName())
            .lastName(source.getLastName())
            .ldapuser(source.getLdapUser());

    final Title title = source.getTitle();
    if (title != null) {
      builder.title(title.getName());
    }

    final NameAddition nameAddition = source.getNameAddition();
    if (nameAddition != null) {
      builder.nameAddition(nameAddition.getName());
    }

    return builder.build();
  }
}
