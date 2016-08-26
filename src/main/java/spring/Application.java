package spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

/**
 * © 2016 org.bytewood
 */
@SpringBootApplication
@RestController
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RequestMapping(value="/echo")
    public String echo(@RequestParam(name = "message") String message) {
        return message + " " + ZonedDateTime.now() + System.getProperty("line.separator");
    }
}
