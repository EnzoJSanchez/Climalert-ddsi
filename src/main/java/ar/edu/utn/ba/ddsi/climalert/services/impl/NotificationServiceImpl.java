package ar.edu.utn.ba.ddsi.climalert.services.impl;

import ar.edu.utn.ba.ddsi.climalert.models.entities.notification.Recipient;
import ar.edu.utn.ba.ddsi.climalert.models.entities.weather.Alert;
import ar.edu.utn.ba.ddsi.climalert.models.entities.weather.AlertState;
import ar.edu.utn.ba.ddsi.climalert.models.entities.weather.WeatherCondition;
import ar.edu.utn.ba.ddsi.climalert.repositories.NotificationRepository;
import ar.edu.utn.ba.ddsi.climalert.services.NotificationService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NotificationServiceImpl implements NotificationService {
    private final List<Recipient> recipients;
    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(List<Recipient> recipients, NotificationRepository notificationRepository) {
        this.recipients = recipients;
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void warnAlert(WeatherCondition weatherData) {
        Alert alert = createAlert(weatherData);

        recipients.stream().forEach(r -> r.beNotifyBy(alert));

        notificationRepository.saveAlert(alert);
    }

    public Alert createAlert(WeatherCondition w){
        return new Alert(null, AlertState.PENDING, w.getName(), w.getRegion(),
                w.getCountry(), w.getLocaltime(), w.getLastUpdated(), w.getTemperature(),
                w.getCondition(), w.getWindKph(), w.getPrecipMm(), w.getHumidity(),
                w.getCloud(), w.getFeelslikeC(), w.getVisKm());
    }
}
