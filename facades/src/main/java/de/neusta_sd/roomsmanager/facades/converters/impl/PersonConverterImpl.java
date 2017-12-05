package de.neusta_sd.roomsmanager.facades.converters.impl;

import de.neusta_sd.roomsmanager.core.entities.NameAddition;
import de.neusta_sd.roomsmanager.core.entities.Person;
import de.neusta_sd.roomsmanager.core.entities.Title;
import de.neusta_sd.roomsmanager.facades.converters.PersonConverter;
import de.neusta_sd.roomsmanager.facades.dto.PersonDto;
import org.springframework.stereotype.Component;

/**
 * Created by Adrian Tello on 05/12/2017.
 */
@Component
public class PersonConverterImpl implements PersonConverter {
    @Override
    public PersonDto convert(final Person source) throws ConversionException {
        final String firstName = source.getFirstName();
        final String lastName = source.getLastName();
        final Title title = source.getTitle();
        final String titleStr = (title == null) ? null : title.getName();
        final NameAddition nameAddition = source.getNameAddition();
        final String nameAdditionStr = (nameAddition == null) ? null : nameAddition.getName();
        final String ldapuser = source.getLdapUser();

        return new PersonDto(firstName, lastName, titleStr, nameAdditionStr, ldapuser);
    }
}
