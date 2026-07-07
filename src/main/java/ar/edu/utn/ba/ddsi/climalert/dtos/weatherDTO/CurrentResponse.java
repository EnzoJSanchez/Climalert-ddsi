package ar.edu.utn.ba.ddsi.climalert.dtos.weatherDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CurrentResponse(
   @JsonProperty("temp_c") Double temperature,
   @JsonProperty("humidity") Double humidity
) {}
