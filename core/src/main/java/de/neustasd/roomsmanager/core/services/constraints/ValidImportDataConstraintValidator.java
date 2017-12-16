package de.neustasd.roomsmanager.core.services.constraints;

import de.neustasd.roomsmanager.core.services.ImportService;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/** Created by adria on 13/12/2017. */
public class ValidImportDataConstraintValidator
    implements ConstraintValidator<ValidImportDataConstraint, ImportService.ImportData> {

  private static final String ROOM_NUMBER_REPEATED_DEFAULT_MSG =
      "Room number \"{0}\" found multiple times.";
  private static final String PERSON_REPEATED_DEFAULT_MSG = "Repeated person: {0} found {1} times.";

  @Override
  public void initialize(ValidImportDataConstraint constraintAnnotation) {}

  @Override
  public boolean isValid(ImportService.ImportData value, ConstraintValidatorContext context) {

    final List<ImportService.RoomData> roomDataList = value.getRoomDataList();

    final boolean hasNoRepeatedPersons = hasNoRepeatedPersons(roomDataList, context);
    final boolean hasUniqueRoomNumbers = validateUniqueRoomNumber(roomDataList, context);

    return hasNoRepeatedPersons && hasUniqueRoomNumbers;
  }

  private boolean validateUniqueRoomNumber(
      final List<ImportService.RoomData> roomDataList, final ConstraintValidatorContext context) {
    boolean isValid = true;

    final Set<String> roomNumbersSet = new HashSet<>();
    for (int i = 0; i < roomDataList.size(); i++) {
      final ImportService.RoomData roomData = roomDataList.get(i);

      final String number = roomData.getNumber();
      if (!roomNumbersSet.add(number)) {
        final MessageFormat messageFormat = new MessageFormat(ROOM_NUMBER_REPEATED_DEFAULT_MSG);
        final String message = messageFormat.format(new Object[] {number});

        context.disableDefaultConstraintViolation();
        context
            .buildConstraintViolationWithTemplate(message)
            .addPropertyNode("roomDataList")
            .addPropertyNode("number")
            .inIterable()
            .atIndex(i)
            .addConstraintViolation();

        isValid = false;
      }
    }

    return isValid;
  }

  private boolean hasNoRepeatedPersons(
      final List<ImportService.RoomData> roomDataList, final ConstraintValidatorContext context) {
    boolean isValid = true;

    final Map<ImportService.PersonData, Long> numbersCount =
        roomDataList
            .stream()
            .flatMap(roomData -> roomData.getPersonDataList().stream())
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    for (Map.Entry<ImportService.PersonData, Long> personDataCountEntry : numbersCount.entrySet()) {
      final Long count = personDataCountEntry.getValue();

      if (count > 1) {
        final ImportService.PersonData personData = personDataCountEntry.getKey();

        isValid = false;

        final MessageFormat messageFormat = new MessageFormat(PERSON_REPEATED_DEFAULT_MSG);
        final String message = messageFormat.format(new Object[] {personData, count});

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
      }
    }

    return isValid;
  }
}
