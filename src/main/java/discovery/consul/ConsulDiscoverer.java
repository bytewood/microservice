package discovery.consul;

import discovery.Discoverer;
import discovery.DiscoveryException;
import lombok.SneakyThrows;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.net.URI;
import java.net.URL;
import java.util.List;

/**
 * © 2016 org.bytewood
 */
public class ConsulDiscoverer implements Discoverer {

    private DiscoveryClient discoveryClient;

    public ConsulDiscoverer(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @Override
    @SneakyThrows
    public URI locate(String microservice) {
        List<ServiceInstance> microservices = discoveryClient.getInstances(microservice);
        if ((microservices != null) && !microservices.isEmpty() ) {
            return microservices.get(0).getUri();
        } else {
            throw new DiscoveryException(microservice + " is Undiscoverable") ;
        }
    }
}
