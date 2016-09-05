package service;

import discovery.Discoverer;
import discovery.MicroserviceSettings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestOperations;

import java.net.URI;

/**
 * © 2016 org.bytewood
 */
@Slf4j
public class DefaultLoopService implements LoopService {

    private RestOperations http;
    private Discoverer discoverer;
    private MicroserviceSettings microserviceASettings;

    public DefaultLoopService(RestOperations http, Discoverer discoverer, MicroserviceSettings microserviceASettings) {
        this.http = http;
        this.discoverer = discoverer;
        this.microserviceASettings = microserviceASettings;
    }

    @Override
    public String loop(String message) {
        URI uri = discoverer.locate("microservice-a");
        String url = resolveURL(uri);
        log.debug("located microservice-a @ " + url);
        return http.getForObject(url, String.class, message);
    }

    String resolveURL(URI baseUri) {
        return baseUri.resolve(microserviceASettings.getContext()+"/loop").toString();
    }
}
