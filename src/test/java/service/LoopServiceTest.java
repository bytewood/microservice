package service;

import discovery.Discoverer;
import discovery.MicroserviceSettings;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestOperations;

import java.net.URI;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

/**
 * © 2016 org.bytewood
 */
public class LoopServiceTest {

    private DefaultLoopService testObject;
    private MicroserviceSettings microserviceASettings;

    @Mock
    private Discoverer discoverer;

    @Mock
    private RestOperations http;

    public LoopServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void setUp() throws Exception {
        microserviceASettings = MicroserviceSettings.builder()
            .protocol("http")
                .ip("localhost")
                .port(8888)
                .context("/api/0.1")
                .build();

        when(discoverer.locate(anyString()))
                .thenReturn(microserviceASettings.uri());
        when(http.getForObject(anyString(), any(), endsWith("test message")))
                .thenReturn("found microservice-a");

        testObject = new DefaultLoopService(http, discoverer, microserviceASettings);
    }

    @Test
    public void resolveURL() throws Exception {
        String actual = testObject.resolveURL(URI.create("http://localhost:8888"));
        assertThat(actual).isEqualTo("http://localhost:8888/api/0.1/loop");
    }

    @Test
    public void loop() throws Exception {
        String actual = testObject.loop("test message");
        assertThat(actual).isEqualTo("found microservice-a");
    }


}
