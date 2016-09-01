package spring;

import discovery.DefaultDiscoverer;
import discovery.Discoverer;
import discovery.MicroserviceSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.DefaultLoopService;
import service.LoopService;
import spring.model.Echo;
import spring.model.Query;

import java.time.ZonedDateTime;

/**
 * © 2016 org.bytewood
 */
@SpringBootApplication
@RestController
public class Application {

    @Autowired
    Settings settings;

    @Autowired LoopService loopService;

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RequestMapping(value="/echo")
    public String echo(@RequestParam(name = "message", required = false, defaultValue = "") String message) {
        return message + " " + ZonedDateTime.now() + " " + settings.getAbc() + System.getProperty("line.separator");
    }


    @RequestMapping(value="/loop")
    public Echo loop(@RequestParam(name = "message", required = false, defaultValue = "ZZZzzz... ") String message) {
        return Echo.builder()
                .echoing(message)
                .from("microservice")
                .instance(System.getProperty("hostname"))
                .timestamp(ZonedDateTime.now())
                .serviceQuery(
                        Query.builder()
                                .request("microservice-a")
                                .response(loopService.loop(message))
                                .build()
                ).build();

    }

    @ConfigurationProperties(prefix = "loopservice"
            , locations = "${spring.config.location:classpath:}/application.yml")
    @Bean
    public MicroserviceSettings microserviceSettings(){
        return new MicroserviceSettings();
    }

    @Bean
    public Discoverer discoverer(MicroserviceSettings microserviceSettings) {
        return new DefaultDiscoverer(microserviceSettings);
    }

    @Bean
    LoopService defaultLoopService(Discoverer discoverer) {
        return new DefaultLoopService(discoverer);
    }
}
