package de.neusta_sd.roomsmanager.core.services.converters.impl;

import de.neusta_sd.roomsmanager.core.entities.NameAddition;
import de.neusta_sd.roomsmanager.core.entities.repositories.NameAdditionRepository;
import de.neusta_sd.roomsmanager.core.services.converters.NameAdditionNameConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by Adrian Tello on 01/12/2017.
 */
@Component
public class NameAdditionNameConverterImpl implements NameAdditionNameConverter {

    @Autowired
    private NameAdditionRepository nameAdditionRepository;

    @Override
    public NameAddition convert(String source) throws ConversionException {
        final Optional<NameAddition> nameAdditionOptional = nameAdditionRepository.findOneByName(source);
        if (!nameAdditionOptional.isPresent()) {
            throw new ConversionException("No name addition entity found for [" + source + "]");
        }

        return nameAdditionOptional.get();
    }
}
