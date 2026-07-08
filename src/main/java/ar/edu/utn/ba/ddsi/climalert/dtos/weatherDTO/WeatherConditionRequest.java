package ar.edu.utn.ba.ddsi.climalert.dtos.weatherDTO;

public record WeatherConditionRequest(
    LocationRequest location,
    CurrentRequest current
) {}
