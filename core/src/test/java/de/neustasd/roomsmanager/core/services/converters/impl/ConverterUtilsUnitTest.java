package de.neustasd.roomsmanager.core.services.converters.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import de.neustasd.roomsmanager.core.services.converters.Converter;
import de.neustasd.roomsmanager.core.services.converters.ConverterUtils;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

/** Created by Adrian Tello on 16/12/2017. */
public class ConverterUtilsUnitTest {
  private final NoOpConverter noOpConverter = new NoOpConverter();

  @Test
  public void testEmpty() {
    // Prepare
    final List<Object> objectList = Collections.emptyList();

    // Test
    final List<Object> convertedObjects = ConverterUtils.convertAll(objectList, noOpConverter);

    // Verify
    assertNotNull(convertedObjects);
    assertEquals(0, convertedObjects.size());
  }

  @Test
  public void testMultiple() {
    // Prepare
    final String object1 = "object1";
    final String object2 = "object2";
    final List<Object> objectList = Arrays.asList(object1, object2);

    // Test
    final List<Object> convertedObjects = ConverterUtils.convertAll(objectList, noOpConverter);

    // Verify
    assertNotNull(convertedObjects);
    assertEquals(convertedObjects, convertedObjects);
  }

  private static class NoOpConverter implements Converter<Object, Object> {

    @Override
    public Object convert(Object source) throws ConversionException {
      return source;
    }
  }
}
