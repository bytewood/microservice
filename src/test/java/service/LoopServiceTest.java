package service;

import discovery.Discoverer;
import discovery.MicroserviceSettings;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestOperations;
import service.model.Echo;
import service.model.Microservice;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * © 2016 org.bytewood
 */
public class LoopServiceTest {

    private DefaultLoopService testObject;

    @Mock
    private Discoverer discoverer;

    @Mock
    private RestOperations http;

    public LoopServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void setUp() throws Exception {
        MicroserviceSettings microserviceASettings = MicroserviceSettings.builder()
                .protocol("http")
                .ip("localhost")
                .port(8888)
                .context("/api/0.1")
                .build();

        Microservice microservice = new Microservice();
        microservice.setName("microservice-a");

        when(discoverer.locate(anyString()))
                .thenReturn(microserviceASettings.uri());
        when(http.getForObject(anyString(), any()))
                .thenReturn(microservice);

        testObject = new DefaultLoopService(http, discoverer, microserviceASettings, "test-host-name");
    }

    @Test
    public void resolveURL() throws Exception {
        String actual = testObject.resolveURL(URI.create("http://localhost:8888"));
        assertThat(actual).isEqualTo("http://localhost:8888/api/0.1/loop");
    }

    @Test
    public void loop() throws Exception {
        Echo actual = testObject.loop("test message");
        assertThat(actual.getServiceQueries()).hasSize(1);
        assertThat(actual.getEchoing()).isEqualTo("test message");
    }

}
