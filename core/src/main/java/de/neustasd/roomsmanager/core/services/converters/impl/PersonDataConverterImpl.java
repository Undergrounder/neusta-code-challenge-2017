package de.neustasd.roomsmanager.core.services.converters.impl;

import de.neustasd.roomsmanager.core.entities.NameAddition;
import de.neustasd.roomsmanager.core.entities.Person;
import de.neustasd.roomsmanager.core.entities.Title;
import de.neustasd.roomsmanager.core.services.ImportService;
import de.neustasd.roomsmanager.core.services.converters.NameAdditionNameConverter;
import de.neustasd.roomsmanager.core.services.converters.PersonDataConverter;
import de.neustasd.roomsmanager.core.services.converters.TitleNameConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** Created by Adrian tello on 01/12/2017. */
@Component
public class PersonDataConverterImpl implements PersonDataConverter {

  private NameAdditionNameConverter nameAdditionNameConverter;

  private TitleNameConverter titleNameConverter;

  @Autowired
  public PersonDataConverterImpl(
      NameAdditionNameConverter nameAdditionNameConverter, TitleNameConverter titleNameConverter) {
    this.nameAdditionNameConverter = nameAdditionNameConverter;
    this.titleNameConverter = titleNameConverter;
  }

  @Override
  public Person convert(ImportService.PersonData source) throws ConversionException {
    final Person target = new Person();
    target.setFirstName(source.getFirstName());
    target.setLastName(source.getLastName());
    target.setNameAddition(convertNameAddition(source.getNameAddition()));
    target.setTitle(convertTitle(source.getTitle()));
    target.setLdapUser(source.getLdapUser());

    return target;
  }

  private NameAddition convertNameAddition(final String source) {
    NameAddition target = null;

    if (source != null) {
      target = getNameAdditionNameConverter().convert(source);
    }

    return target;
  }

  private Title convertTitle(final String source) {
    Title target = null;

    if (source != null) {
      target = titleNameConverter.convert(source);
    }

    return target;
  }

  private NameAdditionNameConverter getNameAdditionNameConverter() {
    return nameAdditionNameConverter;
  }

  private TitleNameConverter getTitleNameConverter() {
    return titleNameConverter;
  }
}
