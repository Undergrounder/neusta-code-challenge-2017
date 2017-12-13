package de.neusta_sd.roomsmanager.core.services.constraints;

import de.neusta_sd.roomsmanager.core.services.ImportService;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by adria on 13/12/2017.
 */
public class ValidImportDataConstraintValidator implements ConstraintValidator<ValidImportDataConstraint, ImportService.ImportData> {

    private final static String ROOM_NUMBER_REPEATED_DEFAULT_MSG = "Room number \"{0}\" found {1} times.";
    private final static String PERSON_REPEATED_DEFAULT_MSG ="Repeated person: {0} found {1} times.";

    @Override
    public void initialize(ValidImportDataConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(ImportService.ImportData value, ConstraintValidatorContext context) {

        final List<ImportService.RoomData> roomDataList = value.getRoomDataList();

        final boolean hasUniqueRoomNumbers = validateUniqueRoomNumber(roomDataList, context);
        final boolean hasNoRepeatedPersons = hasNoRepeatedPersons(roomDataList, context);

        return hasUniqueRoomNumbers && hasNoRepeatedPersons;
    }

    private boolean validateUniqueRoomNumber(final List<ImportService.RoomData> roomDataList, final ConstraintValidatorContext context) {
        boolean isValid = true;

        final Map<String, Long> numbersCount = roomDataList.stream().map(ImportService.RoomData::getNumber).collect(
                Collectors.groupingBy(Function.identity(), Collectors.counting())
        );

        for(Map.Entry<String, Long> numbersCountEntry: numbersCount.entrySet()){
            final Long count = numbersCountEntry.getValue();

            if(count > 1){
                final String number = numbersCountEntry.getKey();

                isValid = false;

                final MessageFormat messageFormat = new MessageFormat(ROOM_NUMBER_REPEATED_DEFAULT_MSG);
                final String message = messageFormat.format(new Object[]{number, count});

                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
            }
        }

        return isValid;
    }

    private boolean hasNoRepeatedPersons(final List<ImportService.RoomData> roomDataList, final ConstraintValidatorContext context)
    {
        boolean isValid = true;

        final Map<ImportService.PersonData, Long> numbersCount = roomDataList.stream()
            .flatMap(roomData -> roomData.getPersonDataList().stream())
            .collect(
                Collectors.groupingBy(Function.identity(), Collectors.counting())
        );

        for(Map.Entry<ImportService.PersonData, Long> personDataCountEntry: numbersCount.entrySet()){
            final Long count = personDataCountEntry.getValue();

            if(count > 1){
                final ImportService.PersonData personData = personDataCountEntry.getKey();

                isValid = false;

                final MessageFormat messageFormat = new MessageFormat(PERSON_REPEATED_DEFAULT_MSG);
                final String message = messageFormat.format(new Object[]{personData, count});

                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
            }
        }

        return isValid;
    }
}
