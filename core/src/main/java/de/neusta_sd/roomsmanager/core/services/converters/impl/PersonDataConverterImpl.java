package de.neusta_sd.roomsmanager.core.services.converters.impl;

import de.neusta_sd.roomsmanager.core.entities.Person;
import de.neusta_sd.roomsmanager.core.services.ImportService;
import de.neusta_sd.roomsmanager.core.services.converters.NameAdditionNameConverter;
import de.neusta_sd.roomsmanager.core.services.converters.PersonDataConverter;
import de.neusta_sd.roomsmanager.core.services.converters.TitleNameConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Adrian tello on 01/12/2017.
 */
@Component
public class PersonDataConverterImpl implements PersonDataConverter {

    @Autowired
    private NameAdditionNameConverter nameAdditionNameConverter;

    @Autowired
    private TitleNameConverter titleNameConverter;

    @Override
    public Person convert(ImportService.PersonData source) throws ConversionException {
        final Person target = new Person();
        target.setFirstName(source.getFirstName());
        target.setLastName(source.getLastName());
        target.setNameAddition(nameAdditionNameConverter.convert(source.getNameAddition()));
        target.setTitle(titleNameConverter.convert(source.getTitle()));
        target.setLdapUser(source.getLdapUser());

        return target;
    }
}
