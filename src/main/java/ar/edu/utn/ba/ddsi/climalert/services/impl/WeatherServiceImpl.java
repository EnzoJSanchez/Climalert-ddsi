package ar.edu.utn.ba.ddsi.climalert.services.impl;

import ar.edu.utn.ba.ddsi.climalert.configs.RestClimalertProperties;
import ar.edu.utn.ba.ddsi.climalert.dtos.weatherDTO.WeatherConditionResponse;
import ar.edu.utn.ba.ddsi.climalert.models.entities.WeatherCondition;
import ar.edu.utn.ba.ddsi.climalert.repositories.WeatherRepository;
import ar.edu.utn.ba.ddsi.climalert.services.WeatherService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class WeatherServiceImpl implements WeatherService {
    private final RestTemplate restTemplate;
    private final RestClimalertProperties propiedades;
    private final WeatherRepository weatherRepository;

    public WeatherServiceImpl(RestTemplate restTemplate, RestClimalertProperties restClimalertProperties, WeatherRepository weatherRepository) {
        this.restTemplate = restTemplate;
        this.propiedades = restClimalertProperties;
        this.weatherRepository = weatherRepository;
    }


    @Override
    @Scheduled(fixedRate = 300000)
    public void getAndSaveCurrentWeatherData() {
        URI uri = UriComponentsBuilder.fromUriString(propiedades.getBaseUrl())
                        .queryParam("key", propiedades.getApiKey())
                        .queryParam("q", propiedades.getCity())
                        .queryParam("lang", "en")
                        .build()
                        .toUri();
        WeatherConditionResponse weatherData = restTemplate.getForObject(uri, WeatherConditionResponse.class);

        WeatherCondition weather = new WeatherCondition(null, weatherData.location().name(), weatherData.location().country(), weatherData.current().temperature(), weatherData.current().humidity());

        weatherRepository.saveWeather(weather);
    }


}
