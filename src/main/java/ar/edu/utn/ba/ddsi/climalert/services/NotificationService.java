package ar.edu.utn.ba.ddsi.climalert.services;

import ar.edu.utn.ba.ddsi.climalert.models.entities.weather.WeatherCondition;

public interface NotificationService {
    void warnAlert(WeatherCondition weatherData);
}
