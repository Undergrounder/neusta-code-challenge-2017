package de.neustasd.roomsmanager.facades.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import de.neustasd.roomsmanager.core.services.ImportService;
import de.neustasd.roomsmanager.core.services.data.ImportData;
import de.neustasd.roomsmanager.core.services.data.ImportResultData;
import de.neustasd.roomsmanager.facades.ImportFacade;
import de.neustasd.roomsmanager.facades.converters.ImportResultDataConverter;
import de.neustasd.roomsmanager.facades.dto.ImportResultDto;
import de.neustasd.roomsmanager.facades.imprt.converter.CsvImportDataConverter;
import de.neustasd.roomsmanager.facades.imprt.csv.parser.ImportCsvParser;
import de.neustasd.roomsmanager.facades.imprt.csv.parser.data.CsvImportData;
import java.io.IOException;
import java.io.InputStream;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/** Created by Adrian Tello on 16/12/2017. */
@RunWith(MockitoJUnitRunner.class)
public class ImportFacadeImplUnitTest {
  private ImportFacade importFacade;

  @Mock private ImportCsvParser importCsvParser;

  @Mock private CsvImportDataConverter csvImportDataConverter;

  @Mock private ImportService importService;

  @Mock private ImportResultDataConverter importResultDataConverter;

  @Before
  public void setUp() throws Exception {
    importFacade =
        new ImportFacadeImpl(
            importCsvParser, csvImportDataConverter, importService, importResultDataConverter);
  }

  @Test
  public void testEmptyFile()
      throws IOException, ImportFacade.ImportException, ImportCsvParser.CsvParsingException,
          ImportService.ImportException {
    // Prepare
    try (final InputStream fileStream =
        ImportFacadeImplUnitTest.class.getResourceAsStream("/imports/empty.csv")) {
      final CsvImportData csvImportData = CsvImportData.builder().build();
      when(importCsvParser.parse(fileStream)).thenReturn(csvImportData);

      final ImportData importData = ImportData.builder().build();
      when(csvImportDataConverter.convert(csvImportData)).thenReturn(importData);

      final ImportResultData importResultData =
          ImportResultData.builder().persons(0).rooms(0).build();

      when(importService.importData(importData)).thenReturn(importResultData);

      final ImportResultDto importResultDto = ImportResultDto.builder().persons(0).rooms(0).build();
      when(importResultDataConverter.convert(importResultData)).thenReturn(importResultDto);

      // Test
      final ImportResultDto importResult = importFacade.importStream(fileStream);

      // Verify
      assertEquals(importResultDto, importResult);
    }
  }
}
