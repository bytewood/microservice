package service;

import discovery.Discoverer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import java.net.URL;

/**
 * © 2016 org.bytewood
 */
@Slf4j
public class DefaultLoopService implements LoopService {

    RestTemplate http = new RestTemplate();
    Discoverer discoverer;

    public DefaultLoopService(Discoverer discoverer) {
        this.discoverer = discoverer;
    }

    @Override
    public String loop(String message) {
        URL url = discoverer.locate("microservice-a");
        log.info("located request " + url.toString());
        return http.getForObject(url.toString(), String.class, message);
    }
}
