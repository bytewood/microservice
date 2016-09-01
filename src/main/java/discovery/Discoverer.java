package discovery;

import java.net.URL;

/**
 * © 2016 org.bytewood
 */
public interface Discoverer {

    URL locate(String microservice);
}
