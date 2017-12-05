package de.neusta_sd.roomsmanager.facades;

import de.neusta_sd.roomsmanager.core.CoreConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CoreConfiguration.class)
@ComponentScan("de.neusta_sd.roomsmanager.facades")
public class FacadesConfiguration {
}
