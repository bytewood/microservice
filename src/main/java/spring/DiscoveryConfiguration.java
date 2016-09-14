package spring;

import discovery.DefaultDiscoverer;
import discovery.Discoverer;
import discovery.MicroserviceASettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * © 2016 org.bytewood
 */
@Configuration
public class DiscoveryConfiguration {

    @Bean
    public Discoverer discoverer(MicroserviceASettings microserviceASettings) {
        return new DefaultDiscoverer(microserviceASettings);
    }

}
