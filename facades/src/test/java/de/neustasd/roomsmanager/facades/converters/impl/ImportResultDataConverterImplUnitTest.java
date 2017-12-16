package de.neustasd.roomsmanager.facades.converters.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import de.neustasd.roomsmanager.core.services.data.ImportResultData;
import de.neustasd.roomsmanager.facades.converters.ImportResultDataConverter;
import de.neustasd.roomsmanager.facades.dto.ImportResultDto;
import org.junit.Test;

/** Created by Adrian Tello on 11/12/2017. */
public class ImportResultDataConverterImplUnitTest {

  private ImportResultDataConverter importResultDataConverter = new ImportResultDataConverterImpl();

  @Test
  public void testConversion() {
    // Prepare
    ImportResultData importResultData = ImportResultData.builder().rooms(15).persons(49).build();

    // Test
    final ImportResultDto importResultDto = importResultDataConverter.convert(importResultData);

    // Verify
    assertNotNull(importResultDto);
    assertEquals(15, importResultDto.getRooms());
    assertEquals(49, importResultDto.getPersons());
  }
}
