package de.neusta_sd.roomsmanager.core.services.converters.impl;

import de.neusta_sd.roomsmanager.core.entities.Title;
import de.neusta_sd.roomsmanager.core.entities.repositories.TitleRepository;
import de.neusta_sd.roomsmanager.core.services.converters.TitleNameConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by Adrian Tello on 01/12/2017.
 */
@Component
public class TitleNameConverterImpl implements TitleNameConverter {

    @Autowired
    private TitleRepository titleRepository;

    @Override
    public Title convert(String source) throws ConversionException {
        final Optional<Title> titleOptional = titleRepository.findOneByName(source);
        if (!titleOptional.isPresent()) {
            throw new ConversionException("No title entity found for [" + source + "]");
        }

        return titleOptional.get();
    }
}
