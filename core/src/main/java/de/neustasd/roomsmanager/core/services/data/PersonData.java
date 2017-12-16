package de.neustasd.roomsmanager.core.services.data;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

/**
 * Created by Adrian Tello on 16/12/2017.
 */
@Builder
@Value
public class PersonData {
    private String title;

    @NonNull
    private String firstName;

    @NonNull private String lastName;

    private String nameAddition;

    @NonNull private String ldapUser;
}
