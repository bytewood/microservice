package service;

import discovery.Discoverer;
import discovery.MicroserviceASettings;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestOperations;
import service.model.Echo;
import service.model.Microservice;
import service.model.Query;

import java.net.URI;
import java.net.URLEncoder;
import java.time.ZonedDateTime;

/**
 * © 2016 org.bytewood
 */
@Slf4j
public class DefaultLoopService implements LoopService {

    public static final String MICROSERVICE = "microservice";
    public static final String MICROSERVICE_A = "microservice-a";
    private RestOperations http;
    private Discoverer discoverer;
    private MicroserviceASettings microserviceASettings;
    private String hostName;

    public DefaultLoopService(RestOperations http, Discoverer discoverer, MicroserviceASettings microserviceASettings
        , String hostName) {
        this.http = http;
        this.discoverer = discoverer;
        this.microserviceASettings = microserviceASettings;
        this.hostName = hostName;
    }

    @Override
    public Echo loop(String message) {
        URI uri = discoverer.locate(MICROSERVICE_A);
        String url = resolveURL(uri, message);
        Microservice m = http.getForObject(url, Microservice.class, message);
        return Echo.builder()
                    .echoing(message)
                    .from(MICROSERVICE)
                    .instance(hostName)
                    .timestamp(ZonedDateTime.now())
                    .serviceQuery(
                            Query.builder()
                                    .request(microserviceASettings.uri().toString())
                                    .response(m)
                                    .build()
                    ).build();

    }

    @Override
    public Echo echo(String message) {
        return Echo.builder()
                .echoing(message)
                .from(MICROSERVICE)
                .instance(hostName)
                .build();
    }

    @SneakyThrows
    String resolveURL(URI baseUri, String message) {
        String m = URLEncoder.encode(message, "UTF-8");
        URI t = baseUri.resolve(new URI(microserviceASettings.getContext()));
        t = t.resolve("/echo?message=" + m);
        String url = t.toString();
        log.debug("Microservice url :: " + url);
        return url;
    }

}
