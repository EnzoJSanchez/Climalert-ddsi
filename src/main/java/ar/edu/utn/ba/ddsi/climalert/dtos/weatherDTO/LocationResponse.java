package ar.edu.utn.ba.ddsi.climalert.dtos.weatherDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LocationResponse(
   @JsonProperty("name") String name,
   @JsonProperty("country") String country
) {}
