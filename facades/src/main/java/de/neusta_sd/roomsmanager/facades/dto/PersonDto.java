package de.neusta_sd.roomsmanager.facades.dto;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

/**
 * Created by Adrian Tello on 05/12/2017.
 */
@Builder
@Value
public class PersonDto {
    private String firstName;
    private String lastName;
    private String title;
    private String nameAddition;

    @NonNull
    private String ldapuser;
}
