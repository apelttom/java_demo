package cz.kpmg.java.app.sample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class JavaAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaAppApplication.class, args);
        log.info("============================================================================");
        log.info("=====================    WELCOME TO SIMPLE JAVA APP    =====================");
        log.info("============================================================================");
        log.info("");
        log.info("           ___    ___    _________    ___    ___     _________       ");
        log.info("          |   |  /  /   |   ___   \\  |   \\  /   |   /   ___   \\");
        log.info("          |   | /  /    |  |   |  |  |    \\/    |  |   /   |__|");
        log.info("          |   |/  /     |  |___|  |  |          |  |  |   ____");
        log.info("          |       \\     |   ______/  |  |\\  /|  |  |  |  |_   |");
        log.info("          |   |\\   \\    |  |         |  | \\/ |  |  |  |    |  |");
        log.info("          |   | \\   \\   |  |         |  |    |  |  |   \\___/  /");
        log.info("          |___|  \\___\\  |__|         |__|    |__|   \\________/");
        log.info("");
        log.info("============================================================================");
        log.info("============================================================================");
        log.debug("my email is msram-ext@kpmg.cz, why not");
        log.trace("TRACE");
        log.debug("DEBUG");
        log.info("INFO");
        log.warn("WARN");
        log.error("ERROR");
    }

}
