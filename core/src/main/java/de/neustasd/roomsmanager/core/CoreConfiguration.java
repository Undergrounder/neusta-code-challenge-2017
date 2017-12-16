package de.neustasd.roomsmanager.core;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("de.neustasd.roomsmanager.core.entities.repositories")
@EnableTransactionManagement
@EntityScan("de.neustasd.roomsmanager.core.entities")
@ComponentScan("de.neustasd.roomsmanager.core")
/** Spring Configuration for the core project. */
public class CoreConfiguration {}
