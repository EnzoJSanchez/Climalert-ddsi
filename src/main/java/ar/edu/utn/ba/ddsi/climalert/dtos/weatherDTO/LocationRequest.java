package ar.edu.utn.ba.ddsi.climalert.dtos.weatherDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LocationRequest(
        @JsonProperty("name") String name,
        @JsonProperty("region") String region,
        @JsonProperty("country") String country,
        @JsonProperty("localtime") String localtime
) {}
