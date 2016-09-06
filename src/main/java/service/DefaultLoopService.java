package service;

import discovery.Discoverer;
import discovery.MicroserviceSettings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestOperations;
import service.model.Echo;
import service.model.Microservice;
import service.model.Query;

import java.net.URI;
import java.time.ZonedDateTime;

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
    public Echo loop(String message) {
        URI uri = discoverer.locate("microservice-a");
        String url = resolveURL(uri);
        Microservice m = http.getForObject(url, Microservice.class, message);
        return Echo.builder()
                    .echoing(message)
                    .from("microservice")
                    .instance(System.getProperty("hostname"))
                    .timestamp(ZonedDateTime.now())
                    .serviceQuery(
                            Query.builder()
                                    .request("microservice-a")
                                    .response(m)
                                    .build()
                    ).build();

    }

    String resolveURL(URI baseUri) {
        return baseUri.resolve(microserviceASettings.getContext()+"/loop").toString();
    }
}
