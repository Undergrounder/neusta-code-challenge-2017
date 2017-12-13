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

    @Override
    public void initialize(ValidImportDataConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(ImportService.ImportData value, ConstraintValidatorContext context) {

        final List<ImportService.RoomData> roomDataList = value.getRoomDataList();

        boolean hasUniqueRoomNumbers = validateUniqueRoomNumber(roomDataList, context);

        return hasUniqueRoomNumbers;
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
}
