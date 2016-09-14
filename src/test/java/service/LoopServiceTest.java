package service;

import discovery.Discoverer;
import discovery.MicroserviceASettings;
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

    private static final String TEST_MESSAGE = "test message";
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
        MicroserviceASettings microserviceASettings = MicroserviceASettings.builder()
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
        String actual = testObject.resolveURL(URI.create("http://localhost:8888"), "testing");
        assertThat(actual).isEqualTo("http://localhost:8888/api/0.1/echo?message=testing");
    }

    @Test
    public void echo() throws Exception {
        Echo actual = testObject.echo(TEST_MESSAGE);
        assertThat(actual.getEchoing()).isEqualTo(TEST_MESSAGE);
    }

    @Test
    public void loop() throws Exception {
        Echo actual = testObject.loop(TEST_MESSAGE);
        assertThat(actual.getServiceQueries()).hasSize(1);
        assertThat(actual.getEchoing()).isEqualTo(TEST_MESSAGE);
    }

}
