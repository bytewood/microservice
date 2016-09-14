package spring;

import discovery.MicroserviceASettings;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

/**
 * © 2016 org.bytewood
 */
@Profile("loadbalancer")
@Configuration
public class LoadbalancerConfiguration {

    @ConfigurationProperties(prefix = "microservice-a"
        , locations = "${spring.config.location:classpath:}/application-loadbalancer.yml")
    @Primary
    @Bean
    public MicroserviceASettings loadbalancerMicroserviceASettings() {
        return MicroserviceASettings.builder().build();
    }

}
