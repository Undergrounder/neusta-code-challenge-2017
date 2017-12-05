package de.neusta_sd.roomsmanager.core.services.converters;

/**
 * Created by Adrian Tello on 01/12/2017.
 */
public interface Converter<S, T> {
    T convert(S source) throws ConversionException;

    class ConversionException extends RuntimeException {
        public ConversionException(String message) {
            super(message);
        }
    }
}
