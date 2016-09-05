package spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RestController;

/**
 * © 2016 org.bytewood
 */
@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class Application {

    private static ConfigurableApplicationContext run;

    private Application() {
        super();
    }

    public static void main(final String[] args) {
        run = SpringApplication.run(Application.class, args);

        Runtime.getRuntime().addShutdownHook(new Thread() {

                                                 @Override
                                                 public void run() {
                                                     run.close();
                                                 }
                                             }
        );
    }

}
