package de.neustasd.roomsmanager.facades.imprt.csv.parser.converter.impl;

import de.neustasd.roomsmanager.core.services.NameAdditionService;
import de.neustasd.roomsmanager.core.services.TitleService;
import de.neustasd.roomsmanager.facades.imprt.csv.parser.ImportCsvParser;
import de.neustasd.roomsmanager.facades.imprt.csv.parser.converter.PersonStringConverter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/** Created by Adrian Tello on 09/12/2017. */
@Component
public class PersonStringConverterImpl implements PersonStringConverter {

  private static final String SPLIT_REGEX = "\\ ";

  private final TitleService titleService;

  private final NameAdditionService nameAdditionService;

  @Autowired
  public PersonStringConverterImpl(
      TitleService titleService, NameAdditionService nameAdditionService) {
    this.titleService = titleService;
    this.nameAdditionService = nameAdditionService;
  }

  @Override
  public ImportCsvParser.CsvPersonData convert(final String source) throws ConversionException {
    // Simple validation
    if (StringUtils.isEmpty(source)) {
      throw new ConversionException("Can't convert from an empty or null value.");
    }

    final String[] splittedSource = source.split(SPLIT_REGEX);
    if (splittedSource.length < 3) {
      throw new ConversionException("The person string must at least have 3 parts.");
    }

    final List<String> splittedSourceList = new ArrayList<>(Arrays.asList(splittedSource));
    return convert(splittedSourceList);
  }

  private ImportCsvParser.CsvPersonData convert(List<String> splittedSource) {
    // Extracts parts
    final ImportCsvParser.CsvPersonData.CsvPersonDataBuilder builder =
        ImportCsvParser.CsvPersonData.builder();

    // Extract ldapUser
    {
      final int tokenIdx = splittedSource.size() - 1;
      final String lastToken = getToken(splittedSource, tokenIdx);
      final String ldapUser = extractLdapUser(lastToken);
      splittedSource.remove(tokenIdx); // Remove last token

      builder.ldapUser(ldapUser);
    }

    // Extract title is present
    final String firstToken = getToken(splittedSource, 0);
    final boolean hasTitle = getTitleService().existsTitleByName(firstToken);

    if (hasTitle) {
      builder.title(firstToken);
      splittedSource.remove(0); // Remove extracted token
    }

    // Extract lastName
    {
      final int tokenIdx = splittedSource.size() - 1;
      final String token = getToken(splittedSource, tokenIdx);
      splittedSource.remove(token);

      builder.lastName(token);
    }
    // Extract nameAddition if present
    {
      final int tokenIdx = splittedSource.size() - 1;
      final String token = getToken(splittedSource, tokenIdx);

      final boolean hasNameAddition = getNameAdditionService().existsNameAddition(token);
      if (hasNameAddition) {
        builder.nameAddition(token);

        splittedSource.remove(tokenIdx);
      }
    }

    // Extract firstName
    {
      String firstName;
      switch (splittedSource.size()) {
        case 1:
          firstName = getToken(splittedSource, 0);
          break;
        case 2:
          firstName = getToken(splittedSource, 0) + ' ' + getToken(splittedSource, 1);
          break;
        default:
          throw new ConversionException("Invalid person string format.");
      }

      builder.firstName(firstName);
    }
    return builder.build();
  }

  private String extractLdapUser(final String ldapUserSource) {
    // Validate
    if (ldapUserSource.length() < 3) {
      throw new ConversionException("The length of the ldapuser source string must be at least 3.");
    }

    if (ldapUserSource.charAt(0) != '('
        || ldapUserSource.charAt(ldapUserSource.length() - 1) != ')') {
      throw new ConversionException(
          "The ldapuser source string must start with \"(\" and end with \")\".");
    }

    // Extract
    return ldapUserSource.substring(1, ldapUserSource.length() - 1);
  }

  private String getToken(final List<String> splittedSource, final int index) {
    if (index < 0 || index > splittedSource.size() - 1) {
      throw new ConversionException("Token not found.");
    }

    return splittedSource.get(index);
  }

  private TitleService getTitleService() {
    return titleService;
  }

  private NameAdditionService getNameAdditionService() {
    return nameAdditionService;
  }
}
