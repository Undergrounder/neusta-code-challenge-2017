package de.neusta_sd.roomsmanager.war;

import de.neusta_sd.roomsmanager.frontend.FrontendConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(FrontendConfiguration.class)
public class WarApplication {
    public static void main(String[] args) {
        SpringApplication.run(WarApplication.class, args);
    }
}