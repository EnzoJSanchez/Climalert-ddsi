package ar.edu.utn.ba.ddsi.climalert.schedules;

import ar.edu.utn.ba.ddsi.climalert.services.WeatherService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WeatherScheduled {

    private final WeatherService weatherService;

    public WeatherScheduled(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Scheduled(fixedRate = 30000)
    public void getCurrentWeatherConditions(){
        weatherService.getAndSaveCurrentWeatherData();
    }

    @Scheduled(fixedRate = 60000)
    public void analyzeCurrentCriticalConditions(){
        weatherService.checkCriticalConditions();
    }
}
