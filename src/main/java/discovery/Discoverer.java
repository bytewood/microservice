package discovery;

import java.net.URI;

/**
 * © 2016 org.bytewood
 */
@FunctionalInterface
public interface Discoverer {

    URI locate(String microservice);
}
