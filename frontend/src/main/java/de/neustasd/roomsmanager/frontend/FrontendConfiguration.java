package de.neustasd.roomsmanager.frontend;

import de.neustasd.roomsmanager.facades.FacadesConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(FacadesConfiguration.class)
@ComponentScan("de.neustasd.roomsmanager.frontend")
/** Spring Configuration for the frontend project. */
public class FrontendConfiguration {}
