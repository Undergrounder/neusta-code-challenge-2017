package de.neusta_sd.roomsmanager.facades.converters;

import de.neusta_sd.roomsmanager.core.services.ImportService;
import de.neusta_sd.roomsmanager.core.services.converters.Converter;
import de.neusta_sd.roomsmanager.facades.dto.ImportResultDto;

/** Created by Adrian Tello on 11/12/2017. */
public interface ImportResultDataConverter
    extends Converter<ImportService.ImportResultData, ImportResultDto> {}
