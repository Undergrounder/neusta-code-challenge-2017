package de.neustasd.roomsmanager.facades;

import de.neustasd.roomsmanager.core.CoreConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CoreConfiguration.class)
@ComponentScan("de.neustasd.roomsmanager.facades")
/**
 * Spring Configuration for the facades project.
 */
public class FacadesConfiguration {}
