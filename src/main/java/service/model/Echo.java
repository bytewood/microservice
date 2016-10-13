package service.model;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * © 2016 org.bytewood
 */
@Data
@Builder
public class Echo {
    private String echoing;
    private ZonedDateTime timestamp;
    private String from;
    private String instance;
    @Singular
    private List<Query> serviceQueries;
}
