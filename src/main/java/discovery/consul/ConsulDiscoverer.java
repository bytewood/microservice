package discovery.consul;

import discovery.Discoverer;
import lombok.SneakyThrows;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

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
    public URL locate(String microservice) {
        List<ServiceInstance> list = discoveryClient.getInstances(microservice);
        if ((list != null) && !list.isEmpty() ) {
            return list.get(0).getUri().toURL();
        } else {
            return null;
        }
    }
}
