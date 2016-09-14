package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.LoopService;
import service.model.Echo;

/**
 * © 2016 org.bytewood
 */
@RestController
public class Resource {

    private LoopService loopService;

    @Autowired
    public Resource(LoopService loopService) {
        this.loopService = loopService;
    }

    @RequestMapping(value="/echo")
    public Echo echo(@RequestParam(name = "message", required = false, defaultValue = "AAAaaa___") String message) {
        return loopService.echo(message);
    }

    @RequestMapping(value="/loop")
    public Echo loop(@RequestParam(name = "message", required = false, defaultValue = "ZZZzzz... ") String message) {
        return loopService.loop(message);
    }

}
