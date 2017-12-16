package de.neustasd.roomsmanager.facades.converters.impl;

import de.neustasd.roomsmanager.core.services.data.ImportResultData;
import de.neustasd.roomsmanager.facades.converters.ImportResultDataConverter;
import de.neustasd.roomsmanager.facades.dto.ImportResultDto;
import org.springframework.stereotype.Component;

/** Created by Adrian Tello on 11/12/2017. */
@Component
public class ImportResultDataConverterImpl implements ImportResultDataConverter {
  @Override
  public ImportResultDto convert(ImportResultData source) throws ConversionException {
    return ImportResultDto.builder().rooms(source.getRooms()).persons(source.getPersons()).build();
  }
}
