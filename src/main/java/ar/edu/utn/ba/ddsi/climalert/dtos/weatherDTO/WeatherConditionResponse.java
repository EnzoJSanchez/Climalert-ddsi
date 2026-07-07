package ar.edu.utn.ba.ddsi.climalert.dtos.weatherDTO;

public record WeatherConditionResponse(
    LocationResponse location,
    CurrentResponse current
) {}
