package discovery;

import lombok.SneakyThrows;

import java.net.URI;

/**
 * © 2016 org.bytewood
 */
public class DefaultDiscoverer implements Discoverer {

    private MicroserviceASettings settings;

    public DefaultDiscoverer(MicroserviceASettings settings) {
        this.settings = settings;
    }

    @Override
    @SneakyThrows
    public URI locate(String microservice) {
        return settings.baseUri();
    }
}
