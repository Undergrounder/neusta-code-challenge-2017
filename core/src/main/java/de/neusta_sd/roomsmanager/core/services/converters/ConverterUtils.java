package de.neusta_sd.roomsmanager.core.services.converters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Adrian Tello on 05/12/2017.
 */
public class ConverterUtils {

    public static <S, T> List<T> convertAll(Collection<S> sourceList, Converter<S, T> converter) throws Converter.ConversionException {
        final List<T> convertedList = new ArrayList<>(sourceList.size());
        for (S sourceItem : sourceList) {
            final T convertedItem = converter.convert(sourceItem);
            convertedList.add(convertedItem);
        }

        return convertedList;
    }
}
