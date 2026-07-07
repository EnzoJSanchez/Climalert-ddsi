package ar.edu.utn.ba.ddsi.climalert.repositories.impl;

import ar.edu.utn.ba.ddsi.climalert.models.entities.weather.WeatherCondition;
import ar.edu.utn.ba.ddsi.climalert.repositories.WeatherRepository;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class WeatherRepositoryImpl implements WeatherRepository {
    private final ConcurrentHashMap<Long, WeatherCondition> weatherConditionsDb = new ConcurrentHashMap<>();
    private final AtomicLong contadorId = new AtomicLong(0); // Empezamos en 0
    private final AtomicLong lastSavedId = new AtomicLong(-1); // Para trackear el último

    @Override
    public WeatherCondition findLastWeatherData() {
        long lastId = lastSavedId.get();
        if (lastId == -1) {
            // TODO: there's no data to return exception
        }
        return weatherConditionsDb.get(lastId);
    }

    @Override
    public WeatherCondition saveWeather(WeatherCondition weatherCondition) {
        if (weatherCondition.getId() != null) {
            // TODO: already has an id exception
        }

        long nextId = contadorId.incrementAndGet();
        weatherCondition.setId(nextId);
        weatherConditionsDb.put(nextId, weatherCondition);

        lastSavedId.set(nextId);

        return weatherCondition;
    }
}