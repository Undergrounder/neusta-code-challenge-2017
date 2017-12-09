package de.neusta_sd.roomsmanager.facades.imprt.converter.impl;

import de.neusta_sd.roomsmanager.core.services.ImportService;
import de.neusta_sd.roomsmanager.core.services.converters.ConverterUtils;
import de.neusta_sd.roomsmanager.facades.imprt.converter.CsvPersonDataConverter;
import de.neusta_sd.roomsmanager.facades.imprt.converter.CsvRoomDataConverter;
import de.neusta_sd.roomsmanager.facades.imprt.csv.parser.ImportCsvParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Adrian Tello on 09/12/2017.
 */
@Component
public class CsvRoomDataConverterImpl implements CsvRoomDataConverter {

    private CsvPersonDataConverter csvPersonDataConverter;

    @Autowired
    public CsvRoomDataConverterImpl(final CsvPersonDataConverter csvPersonDataConverter) {
        this.csvPersonDataConverter = csvPersonDataConverter;
    }

    @Override
    public ImportService.RoomData convert(final ImportCsvParser.CsvRoomData source) throws ConversionException {
        //Get values
        final String sourceNumber = source.getNumber();
        final List<ImportCsvParser.CsvPersonData> sourcePersonDataList = source.getPersonDataList();

        //Convert
        List<ImportService.PersonData> targetPersonDataList = ConverterUtils.convertAll(sourcePersonDataList, getCsvPersonDataConverter());

        //Create object
        return ImportService.RoomData.builder()
                .number(sourceNumber)
                .personDataList(targetPersonDataList)
                .build();
    }

    private CsvPersonDataConverter getCsvPersonDataConverter() {
        return csvPersonDataConverter;
    }
}
