package de.neustasd.roomsmanager.facades.imprt.converter.impl;

import de.neustasd.roomsmanager.core.services.ImportService;
import de.neustasd.roomsmanager.facades.imprt.converter.CsvPersonDataConverter;
import de.neustasd.roomsmanager.facades.imprt.csv.parser.ImportCsvParser;
import org.springframework.stereotype.Component;

/** Created by Adrian Tello on 09/12/2017. */
@Component
public class CsvPersonDataConverterImpl implements CsvPersonDataConverter {
  @Override
  public ImportService.PersonData convert(final ImportCsvParser.CsvPersonData source)
      throws ConversionException {
    return ImportService.PersonData.builder()
        .title(source.getTitle())
        .firstName(source.getFirstName())
        .nameAddition(source.getNameAddition())
        .lastName(source.getLastName())
        .ldapUser(source.getLdapUser())
        .build();
  }
}
