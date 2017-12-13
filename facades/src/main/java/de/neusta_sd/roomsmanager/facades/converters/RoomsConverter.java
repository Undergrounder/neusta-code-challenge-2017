package de.neusta_sd.roomsmanager.facades.converters;

import de.neusta_sd.roomsmanager.core.entities.Room;
import de.neusta_sd.roomsmanager.core.services.converters.Converter;
import de.neusta_sd.roomsmanager.facades.dto.RoomsDto;

import java.util.List;

/**
 * Created by Adrian Tello on 11/12/2017.
 */
public interface RoomsConverter extends Converter<List<Room>, RoomsDto>{
}
