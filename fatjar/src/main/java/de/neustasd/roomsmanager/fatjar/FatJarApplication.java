package de.neustasd.roomsmanager.fatjar;

import de.neustasd.roomsmanager.frontend.FrontendConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(FrontendConfiguration.class)
/**
 * Spring application and configuration for the fatjar project.
 */
public class FatJarApplication {
  public static void main(String[] args) {
    SpringApplication.run(FatJarApplication.class, args);
  }
}
