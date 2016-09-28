package spring;

import discovery.Discoverer;
import discovery.MicroserviceASettings;
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

    @ConfigurationProperties(prefix = "microservice-a")
    @Bean
    public MicroserviceASettings microserviceASettings() {
        return MicroserviceASettings.builder().build();
    }

    @Bean
    LoopService defaultLoopService(RestOperations http, Discoverer discoverer, MicroserviceASettings microserviceASettings
            , String hostName
    ) {
        return new DefaultLoopService(http, discoverer, microserviceASettings, hostName);
    }

}
