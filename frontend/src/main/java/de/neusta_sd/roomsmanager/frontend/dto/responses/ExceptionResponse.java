package de.neusta_sd.roomsmanager.frontend.dto.responses;

import lombok.NonNull;
import lombok.Value;

/**
 * Created by Adrian Tello on 04/12/2017.
 */
@Value
public class ExceptionResponse {
    private int code;

    @NonNull
    private String message;
}
