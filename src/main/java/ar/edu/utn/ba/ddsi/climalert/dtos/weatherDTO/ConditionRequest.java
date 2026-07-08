package ar.edu.utn.ba.ddsi.climalert.dtos.weatherDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ConditionRequest(
        @JsonProperty("text") String text
) {}