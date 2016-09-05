package discovery;

import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;

import java.net.URI;

/**
 * © 2016 org.bytewood
 */
@Data
@Builder
public class MicroserviceSettings {
    private String protocol;
    private String ip;
    private int port;
    private String context;

    @SneakyThrows
    public URI uri() {
        return new URI(protocol, null, ip, port, context, null, null);
    }

    @SneakyThrows
    public URI baseUri() {
        return new URI(protocol, null, ip, port, null, null, null);
    }

}
