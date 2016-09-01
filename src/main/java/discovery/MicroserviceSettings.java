package discovery;

import lombok.Data;

/**
 * © 2016 org.bytewood
 */
@Data
public class MicroserviceSettings {
    private String protocol;
    private String ip;
    private int port;
    private String path;
}
