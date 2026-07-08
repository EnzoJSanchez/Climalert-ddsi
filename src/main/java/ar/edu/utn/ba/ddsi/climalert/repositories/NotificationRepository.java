package ar.edu.utn.ba.ddsi.climalert.repositories;

import ar.edu.utn.ba.ddsi.climalert.models.entities.weather.Alert;

public interface NotificationRepository {
    void saveAlert(Alert alert);
}
