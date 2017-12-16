package de.neustasd.roomsmanager.facades.imprt.csv.parser.data;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

/**
 * Created by Adrian Tello on 16/12/2017.
 */
@Builder
@Value
public class CsvPersonData {
    private String title;
    private String firstName;
    private String lastName;
    private String nameAddition;
    @NonNull
    private String ldapUser;
}
