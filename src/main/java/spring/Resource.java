package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.LoopService;
import spring.model.Echo;
import spring.model.Query;

import java.time.ZonedDateTime;

/**
 * © 2016 org.bytewood
 */
@RestController
public class Resource {

    private Settings settings;
    private LoopService loopService;

    @Autowired
    public Resource(Settings settings, LoopService loopService) {
        this.settings = settings;
        this.loopService = loopService;
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

}
