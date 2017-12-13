package de.neusta_sd.roomsmanager.frontend.controllers.exceptions;

/**
 * Created by Adrian Tello on 13/12/2017.
 */
public class RoomNotFoundException extends NotFoundException{
    public RoomNotFoundException(final String message) {
        super(message);
    }
}
