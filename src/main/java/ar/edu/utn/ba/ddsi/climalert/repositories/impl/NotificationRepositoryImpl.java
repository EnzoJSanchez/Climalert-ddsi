package ar.edu.utn.ba.ddsi.climalert.repositories.impl;

import ar.edu.utn.ba.ddsi.climalert.models.entities.weather.Alert;
import ar.edu.utn.ba.ddsi.climalert.repositories.NotificationRepository;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class NotificationRepositoryImpl implements NotificationRepository {

    private final ConcurrentHashMap<Long, Alert> alertsDb = new ConcurrentHashMap<>();
    private final AtomicLong counterId = new AtomicLong(0);

    @Override
    public void saveAlert(Alert alert) {
        long nextId = counterId.incrementAndGet();
        alert.setId(nextId);

        alertsDb.put(alert.getId(), alert);
    }
}
