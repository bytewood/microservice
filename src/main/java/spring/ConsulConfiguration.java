package spring;

import discovery.Discoverer;
import discovery.consul.ConsulDiscoverer;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

/**
 * © 2016 org.bytewood
 */
@Profile("consul")
@Configuration
public class ConsulConfiguration {

    @Primary
    @Bean
    public Discoverer consulDiscoverer(DiscoveryClient discoveryClient) {
        return new ConsulDiscoverer(discoveryClient);
    }
}
