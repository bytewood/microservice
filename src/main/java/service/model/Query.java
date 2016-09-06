package service.model;

import lombok.Builder;
import lombok.Data;

/**
 * © 2016 org.bytewood
 */
@Data
@Builder
public class Query {
    private String request;
    private Microservice response;
}
