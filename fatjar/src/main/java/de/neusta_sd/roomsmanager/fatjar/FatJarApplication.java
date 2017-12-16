package de.neusta_sd.roomsmanager.fatjar;

import de.neusta_sd.roomsmanager.frontend.FrontendConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/** Created by Adrian Tello on 01/12/2017. */
@SpringBootApplication
@Import(FrontendConfiguration.class)
public class FatJarApplication {
  public static void main(String[] args) {
    SpringApplication.run(FatJarApplication.class, args);
  }
}
