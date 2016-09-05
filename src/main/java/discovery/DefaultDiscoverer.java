package discovery;

import lombok.SneakyThrows;

import java.net.URI;
import java.net.URL;

/**
 * © 2016 org.bytewood
 */
public class DefaultDiscoverer implements Discoverer {

    private MicroserviceSettings settings;

    public DefaultDiscoverer(MicroserviceSettings settings) {
        this.settings = settings;
    }

    @Override
    @SneakyThrows
    public URI locate(String microservice) {
        return settings.baseUri();
    }
}
