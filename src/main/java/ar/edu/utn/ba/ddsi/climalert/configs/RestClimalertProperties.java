package ar.edu.utn.ba.ddsi.climalert.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rest-climalert")
@Data
public class RestClimalertProperties {

    private String baseUrl;
}
