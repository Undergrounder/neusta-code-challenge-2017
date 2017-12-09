package de.neusta_sd.roomsmanager.core.services;

import de.neusta_sd.roomsmanager.core.entities.NameAddition;

import java.util.Optional;

/**
 * Created by Adrian Tello on 09/12/2017.
 */
public interface NameAdditionService {
    Optional<NameAddition> getNameAddition(final String name);

    boolean existsNameAddition(final String name);
}
