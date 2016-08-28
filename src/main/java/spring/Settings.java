package spring;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * © 2016 org.bytewood
 */
@Component
@ConfigurationProperties(prefix = "abc", locations = "${spring.config.location:classpath:}/application.yml")
@Data
public class Settings {
    String abc;
}
