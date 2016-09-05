package spring;

import discovery.Discoverer;
import discovery.MicroserviceSettings;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import service.DefaultLoopService;
import service.LoopService;

/**
 * © 2016 org.bytewood
 */
@Configuration
public class MicroservicesConfiguration {

    @Bean
    public RestOperations http() {
        return new RestTemplate();
    }

    @ConfigurationProperties(prefix = "loopservice"
            , locations = "${spring.config.location:classpath:}/application.yml")
    @Bean
    public MicroserviceSettings microserviceASettings() {
        return MicroserviceSettings.builder().build();
    }

    @Bean
    LoopService defaultLoopService(RestOperations http, Discoverer discoverer, @Qualifier("microserviceASettings") MicroserviceSettings microserviceASettings) {
        return new DefaultLoopService(http, discoverer, microserviceASettings);
    }

}
