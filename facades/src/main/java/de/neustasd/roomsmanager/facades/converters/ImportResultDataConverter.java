package de.neustasd.roomsmanager.facades.converters;

import de.neustasd.roomsmanager.core.services.ImportService;
import de.neustasd.roomsmanager.core.services.converters.Converter;
import de.neustasd.roomsmanager.facades.dto.ImportResultDto;

/** Created by Adrian Tello on 11/12/2017. */
public interface ImportResultDataConverter
    extends Converter<ImportService.ImportResultData, ImportResultDto> {}
