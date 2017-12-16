package de.neusta_sd.roomsmanager.frontend;

import de.neusta_sd.roomsmanager.facades.FacadesConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(FacadesConfiguration.class)
@ComponentScan("de.neusta_sd.roomsmanager.frontend")
public class FrontendConfiguration {}
