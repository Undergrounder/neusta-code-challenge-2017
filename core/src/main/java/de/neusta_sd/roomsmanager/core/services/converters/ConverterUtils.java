package de.neusta_sd.roomsmanager.core.services.converters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Helper methods for converters
 *
 * @author: Adrian Tello
 */
public class ConverterUtils {

  /**
   * Convert all items in the sourceList using the converter.
   *
   * @param sourceList Source items to convert
   * @param converter Converter to use
   *
   * @return The converted items of the sourceList in the same order.
   *
   * @throws Converter.ConversionException Conversion not possible
   */
  public static <S, T> List<T> convertAll(final Collection<S> sourceList, final Converter<S, T> converter)
      throws Converter.ConversionException {
    final List<T> convertedList = new ArrayList<>(sourceList.size());
    for (S sourceItem : sourceList) {
      final T convertedItem = converter.convert(sourceItem);
      convertedList.add(convertedItem);
    }

    return convertedList;
  }
}
