package discovery;

import lombok.SneakyThrows;

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
    public URL locate(String microservice) {
        return new URL(settings.getProtocol(), settings.getIp(), settings.getPort(), settings.getPath());
    }
}
