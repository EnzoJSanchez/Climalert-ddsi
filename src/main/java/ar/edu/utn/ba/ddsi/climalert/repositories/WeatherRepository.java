package ar.edu.utn.ba.ddsi.climalert.repositories;

import ar.edu.utn.ba.ddsi.climalert.models.entities.weather.WeatherCondition;

import java.util.Optional;

public interface WeatherRepository {
    Optional<WeatherCondition> findLastWeatherData();

    WeatherCondition saveWeather(WeatherCondition weatherCondition);
}
