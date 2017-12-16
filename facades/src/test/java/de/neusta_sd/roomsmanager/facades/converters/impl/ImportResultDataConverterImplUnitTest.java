package de.neusta_sd.roomsmanager.facades.converters.impl;

import de.neusta_sd.roomsmanager.core.services.ImportService;
import de.neusta_sd.roomsmanager.facades.converters.ImportResultDataConverter;
import de.neusta_sd.roomsmanager.facades.dto.ImportResultDto;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/** Created by Adrian Tello on 11/12/2017. */
public class ImportResultDataConverterImplUnitTest {

  private ImportResultDataConverter importResultDataConverter = new ImportResultDataConverterImpl();

  @Test
  public void testConversion() {
    // Prepare
    ImportService.ImportResultData importResultData =
        ImportService.ImportResultData.builder().rooms(15).persons(49).build();

    // Test
    final ImportResultDto importResultDto = importResultDataConverter.convert(importResultData);

    // Verify
    assertNotNull(importResultDto);
    assertEquals(15, importResultDto.getRooms());
    assertEquals(49, importResultDto.getPersons());
  }
}
