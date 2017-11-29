package de.neusta_sd.roomsmanager.core;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("de.neusta_sd.roomsmanager.core.entities.repositories")
@EntityScan("de.neusta_sd.roomsmanager.core.entities")
@ComponentScan("de.neusta_sd.roomsmanager.core")
public class CoreConfiguration {
}
