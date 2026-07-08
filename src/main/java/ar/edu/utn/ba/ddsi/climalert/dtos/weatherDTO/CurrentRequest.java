package ar.edu.utn.ba.ddsi.climalert.dtos.weatherDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CurrentRequest(
        @JsonProperty("last_updated") String lastUpdated,
        @JsonProperty("temp_c") Double temperature,
        @JsonProperty("condition") ConditionRequest condition,
        @JsonProperty("wind_kph") Double windKph,
        @JsonProperty("precip_mm") Double precipMm,
        @JsonProperty("humidity") Double humidity,
        @JsonProperty("cloud") Integer cloud,
        @JsonProperty("feelslike_c") Double feelslikeC,
        @JsonProperty("vis_km") Double visKm,
        @JsonProperty("vis_miles") Double visMiles
) {}
