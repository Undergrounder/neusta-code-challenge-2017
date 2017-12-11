package de.neusta_sd.roomsmanager.facades.converters.impl;

import de.neusta_sd.roomsmanager.core.services.ImportService;
import de.neusta_sd.roomsmanager.facades.converters.ImportResultDataConverter;
import de.neusta_sd.roomsmanager.facades.dto.ImportResultDto;
import org.springframework.stereotype.Component;

/**
 * Created by Adrian Tello on 11/12/2017.
 */
@Component
public class ImportResultDataConverterImpl implements ImportResultDataConverter{
    @Override
    public ImportResultDto convert(ImportService.ImportResultData source) throws ConversionException {
        return ImportResultDto.builder()
                .rooms(source.getRooms())
                .persons(source.getPersons())
                .build();
    }
}
