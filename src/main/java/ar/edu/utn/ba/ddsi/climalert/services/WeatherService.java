package ar.edu.utn.ba.ddsi.climalert.services;

import org.springframework.scheduling.annotation.Scheduled;

public interface WeatherService {
    void getAndSaveCurrentWeatherData();

    void checkCriticalConditions();
}
