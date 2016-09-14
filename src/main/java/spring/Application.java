package spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

/**
 * © 2016 org.bytewood
 */
@Slf4j
@SpringBootApplication
@RestController
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public String hostName() {
        String hostname = Optional
                .ofNullable(System.getProperty("HOSTNAME"))
                .orElse(UUID.randomUUID().toString());
        log.debug("microservice instance id :: " + hostname);
        return hostname;
    }
}
