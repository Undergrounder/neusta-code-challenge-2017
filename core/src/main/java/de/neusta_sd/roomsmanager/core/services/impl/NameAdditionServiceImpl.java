package de.neusta_sd.roomsmanager.core.services.impl;

import de.neusta_sd.roomsmanager.core.entities.NameAddition;
import de.neusta_sd.roomsmanager.core.entities.repositories.NameAdditionRepository;
import de.neusta_sd.roomsmanager.core.services.NameAdditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Adrian Tello on 09/12/2017.
 */
@Service
public class NameAdditionServiceImpl implements NameAdditionService {

    private NameAdditionRepository nameAdditionRepository;

    @Autowired
    public NameAdditionServiceImpl(NameAdditionRepository nameAdditionRepository) {
        this.nameAdditionRepository = nameAdditionRepository;
    }

    @Override
    public Optional<NameAddition> getNameAddition(String name) {
        return getNameAdditionRepository().findOneByName(name);
    }

    @Override
    public boolean existsNameAddition(String name) {
        return getNameAddition(name).isPresent();
    }

    protected NameAdditionRepository getNameAdditionRepository() {
        return nameAdditionRepository;
    }
}
