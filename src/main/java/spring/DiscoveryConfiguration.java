package spring;

import discovery.DefaultDiscoverer;
import discovery.Discoverer;
import discovery.MicroserviceSettings;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * © 2016 org.bytewood
 */
@Configuration
public class DiscoveryConfiguration {

    @Bean
    public Discoverer discoverer(@Qualifier("microserviceASettings") MicroserviceSettings microserviceASettings) {
        return new DefaultDiscoverer(microserviceASettings);
    }

}
