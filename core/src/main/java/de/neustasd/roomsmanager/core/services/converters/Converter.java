package de.neustasd.roomsmanager.core.services.converters;

/**
 * Allows converting an object to another class.
 *
 * @param <S> Source class
 * @param <T> Target class
 * @author Adrian Tello
 */
public interface Converter<S, T> {
  /**
   * Converts an object to another class.
   *
   * @param source The source object to convert.
   * @return The converted object
   * @throws ConversionException Conversion not possible
   * @author Adrian Tello
   */
  T convert(S source) throws ConversionException;

  /** Exception thrown when the conversion is not possible. */
  class ConversionException extends RuntimeException {
    public ConversionException(String message) {
      super(message);
    }
  }
}
