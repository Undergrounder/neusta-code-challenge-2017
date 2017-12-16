package de.neustasd.roomsmanager.facades.impl;

import de.neustasd.roomsmanager.core.services.ImportService;
import de.neustasd.roomsmanager.core.services.constraints.RoomNumberConstraint;
import de.neustasd.roomsmanager.core.services.constraints.ValidImportDataConstraint;
import de.neustasd.roomsmanager.core.services.data.ImportData;
import de.neustasd.roomsmanager.core.services.data.ImportResultData;
import de.neustasd.roomsmanager.facades.ImportFacade;
import de.neustasd.roomsmanager.facades.converters.ImportResultDataConverter;
import de.neustasd.roomsmanager.facades.dto.ImportResultDto;
import de.neustasd.roomsmanager.facades.imprt.converter.CsvImportDataConverter;
import de.neustasd.roomsmanager.facades.imprt.csv.parser.ImportCsvParser;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.metadata.ConstraintDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *  {@inheritDoc}
 *
 *  @author Adrian Tello
 */
@Component
public class ImportFacadeImpl implements ImportFacade {

  private ImportCsvParser importCsvParser;

  private CsvImportDataConverter csvImportDataConverter;

  private ImportService importService;

  private ImportResultDataConverter importResultDataConverter;

  @Autowired
  public ImportFacadeImpl(
      ImportCsvParser importCsvParser,
      CsvImportDataConverter csvImportDataConverter,
      ImportService importService,
      ImportResultDataConverter importResultDataConverter) {
    this.importCsvParser = importCsvParser;
    this.csvImportDataConverter = csvImportDataConverter;
    this.importService = importService;
    this.importResultDataConverter = importResultDataConverter;
  }

  @Override
  public ImportResultDto importStream(final InputStream inputStream) throws ImportException {
    final ImportCsvParser.CsvImportData csvImportData = parseCsv(inputStream);
    final ImportData importData = getCsvImportDataConverter().convert(csvImportData);

    final ImportResultData importResultData = doImport(importData);

    return getImportResultDataConverter().convert(importResultData);
  }

  private ImportCsvParser.CsvImportData parseCsv(final InputStream inputStream)
      throws ImportException {
    try {
      return getImportCsvParser().parse(inputStream);
    } catch (ImportCsvParser.CsvParsingException e) {
      throw new ImportException("Invalid csv file", e);
    }
  }

  private ImportResultData doImport(ImportData importData)
      throws ImportException {
    try {
      return getImportService().importData(importData);
    } catch (ImportService.InvalidImportDataException e) {
      final ImportValidationFailedException.FailedValidation failedValidation =
          mapInvalidImportData(e.getConstraintViolations());
      throw new ImportValidationFailedException("Validation failed", e, failedValidation);
    } catch (ImportService.ImportException e) {
      throw new ImportException("Error happened while importing data", e);
    }
  }

  private ImportValidationFailedException.FailedValidation mapInvalidImportData(
      final Collection<ConstraintViolation<ImportData>> constraintViolations) {
    ImportValidationFailedException.FailedValidation failedValidation =
        ImportValidationFailedException.FailedValidation.OTHER;

    for (ConstraintViolation<ImportData> constraintViolation : constraintViolations) {
      ConstraintDescriptor<? extends Annotation> constraintDescriptor =
          constraintViolation.getConstraintDescriptor();

      final Annotation annotation = constraintDescriptor.getAnnotation();

      if (annotation instanceof ValidImportDataConstraint) {
        final Path propertyPath = constraintViolation.getPropertyPath();
        final List<Path.Node> propertyPaths =
            StreamSupport.stream(propertyPath.spliterator(), false).collect(Collectors.toList());
        final Path.Node lastNode = propertyPaths.get(propertyPaths.size() - 1);
        if ("number".equals(lastNode.getName())) {
          failedValidation =
              ImportValidationFailedException.FailedValidation.DUPLICATED_ROOM_NUMBER;
          break;
        } else {
          failedValidation = ImportValidationFailedException.FailedValidation.DUPLICATED_PERSON;
        }

      } else if (annotation instanceof RoomNumberConstraint) {
        failedValidation = ImportValidationFailedException.FailedValidation.INVALID_ENTRY;
        break;
      }
    }

    return failedValidation;
  }

  private ImportCsvParser getImportCsvParser() {
    return importCsvParser;
  }

  private CsvImportDataConverter getCsvImportDataConverter() {
    return csvImportDataConverter;
  }

  private ImportService getImportService() {
    return importService;
  }

  private ImportResultDataConverter getImportResultDataConverter() {
    return importResultDataConverter;
  }
}