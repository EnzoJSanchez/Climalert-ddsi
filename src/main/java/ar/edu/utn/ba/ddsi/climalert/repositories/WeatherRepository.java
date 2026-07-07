package ar.edu.utn.ba.ddsi.climalert.repositories;


import ar.edu.utn.ba.ddsi.climalert.models.entities.weather.WeatherCondition;

public interface WeatherRepository {
    WeatherCondition findLastWeatherData();

    WeatherCondition saveWeather(WeatherCondition weatherCondition);
}
