package de.neusta_sd.roomsmanager.core.services.impl;

import de.neusta_sd.roomsmanager.core.entities.Title;
import de.neusta_sd.roomsmanager.core.entities.repositories.TitleRepository;
import de.neusta_sd.roomsmanager.core.services.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Adrian Tello on 09/12/2017.
 */
@Service
public class TitleServiceImpl implements TitleService {

    private final TitleRepository titleRepository;

    @Autowired
    public TitleServiceImpl(TitleRepository titleRepository) {
        this.titleRepository = titleRepository;
    }

    @Override
    public Optional<Title> getTitle(final String name) {
        return getTitleRepository().findOneByName(name);
    }

    @Override
    public boolean existsTitleByName(String name) {
        return this.getTitle(name).isPresent();
    }

    protected TitleRepository getTitleRepository() {
        return titleRepository;
    }
}
