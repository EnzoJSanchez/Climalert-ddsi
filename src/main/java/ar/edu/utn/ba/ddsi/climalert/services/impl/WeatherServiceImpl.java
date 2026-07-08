package ar.edu.utn.ba.ddsi.climalert.services.impl;

import ar.edu.utn.ba.ddsi.climalert.configs.RestClimalertProperties;
import ar.edu.utn.ba.ddsi.climalert.dtos.weatherDTO.WeatherConditionRequest;
import ar.edu.utn.ba.ddsi.climalert.exceptions.ResourceNotFoundException;
import ar.edu.utn.ba.ddsi.climalert.models.entities.weather.WeatherCondition;
import ar.edu.utn.ba.ddsi.climalert.repositories.WeatherRepository;
import ar.edu.utn.ba.ddsi.climalert.services.NotificationService;
import ar.edu.utn.ba.ddsi.climalert.services.WeatherService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;

@Service
public class WeatherServiceImpl implements WeatherService {
    private final RestTemplate restTemplate;
    private final RestClimalertProperties propiedades;
    private final WeatherRepository weatherRepository;
    private final NotificationService notificationService;

    public WeatherServiceImpl(RestTemplate restTemplate, RestClimalertProperties restClimalertProperties, WeatherRepository weatherRepository, NotificationService notificationService) {
        this.restTemplate = restTemplate;
        this.propiedades = restClimalertProperties;
        this.weatherRepository = weatherRepository;
        this.notificationService = notificationService;
    }


    @Override
    public void getAndSaveCurrentWeatherData() {
        URI uri = UriComponentsBuilder.fromUriString(propiedades.getBaseUrl())
                        .path(propiedades.getPath())
                        .queryParam("key", propiedades.getApiKey())
                        .queryParam("q", propiedades.getCity())
                        .queryParam("lang", "en")
                        .queryParam("aqi", "no")
                        .build()
                        .toUri();
        WeatherConditionRequest weatherData = restTemplate.getForObject(uri, WeatherConditionRequest.class);

        WeatherCondition weather = createWeather(weatherData);

        weatherRepository.saveWeather(weather);
    }


    @Override
    public void checkCriticalConditions(){
        WeatherCondition lastWeatherCondition = getWeatherConditionOrThrow();

        if (lastWeatherCondition.isDangerous()){
            notificationService.warnAlert(lastWeatherCondition);
        }
    }

    public WeatherCondition createWeather(WeatherConditionRequest wd){
        return new WeatherCondition(null, wd.location().name(), wd.location().region(), wd.location().country(), wd.location().localtime(), wd.current().lastUpdated(), wd.current().temperature(), wd.current().condition().text(), wd.current().windKph(), wd.current().precipMm(), wd.current().humidity(), wd.current().cloud(), wd.current().feelslikeC(), wd.current().visKm());
    }

    public WeatherCondition getWeatherConditionOrThrow(){
        return weatherRepository.findLastWeatherData().orElseThrow(() -> new ResourceNotFoundException("No hay condiciones climáticas en el sistema"));
    }
}
