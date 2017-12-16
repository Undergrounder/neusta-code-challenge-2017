package de.neustasd.roomsmanager.war;

import de.neustasd.roomsmanager.frontend.FrontendConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(FrontendConfiguration.class)
/** Spring Configuration for the war project. */
public class WarApplication extends SpringBootServletInitializer {
  public static void main(String[] args) {
    SpringApplication.run(WarApplication.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return builder.sources(WarApplication.class);
  }
}
