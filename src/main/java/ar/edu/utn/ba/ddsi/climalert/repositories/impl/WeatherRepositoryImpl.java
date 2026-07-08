package ar.edu.utn.ba.ddsi.climalert.repositories.impl;

import ar.edu.utn.ba.ddsi.climalert.models.entities.weather.WeatherCondition;
import ar.edu.utn.ba.ddsi.climalert.repositories.WeatherRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class WeatherRepositoryImpl implements WeatherRepository {
    private final ConcurrentHashMap<Long, WeatherCondition> weatherConditionsDb = new ConcurrentHashMap<>();
    private final AtomicLong counterId = new AtomicLong(0);
    private final AtomicLong lastSavedId = new AtomicLong(-1); // Para trackear el último

    @Override
    public Optional<WeatherCondition> findLastWeatherData() {
        long lastId = lastSavedId.get();
        if (lastId == -1) {
            return Optional.empty();
        }
        return Optional.ofNullable(weatherConditionsDb.get(lastId));
    }

    @Override
    public WeatherCondition saveWeather(WeatherCondition weatherCondition) {
        long nextId = counterId.incrementAndGet();
        weatherCondition.setId(nextId);
        weatherConditionsDb.put(nextId, weatherCondition);

        lastSavedId.set(nextId);

        return weatherCondition;
    }
}