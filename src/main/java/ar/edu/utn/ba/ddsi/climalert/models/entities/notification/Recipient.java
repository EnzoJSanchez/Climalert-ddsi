package ar.edu.utn.ba.ddsi.climalert.models.entities.notification;


import ar.edu.utn.ba.ddsi.climalert.models.entities.weather.Alert;
import ar.edu.utn.ba.ddsi.climalert.models.entities.weather.AlertState;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public abstract class Recipient {

    private final JavaMailSender mailSender;
    private String emailTo;

    @Value("${spring.mail.username}")
    private String emailFrom;

    public Recipient(JavaMailSender mailSender, String emailTo) {
        this.mailSender = mailSender;
        this.emailTo = emailTo;
    }

    public void beNotifyBy(Alert alert){
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setFrom(emailFrom);
            mail.setTo(emailTo);
            mail.setSubject("⚠\uFE0F ALERTA: Condición climática crítica detectada");

            // Formateador para las fechas/horas del sistema
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String nowStr = LocalDateTime.now().format(formatter);

            // Construcción del cuerpo del mensaje
            StringBuilder body = new StringBuilder();

            body.append("🌤️ DETALLE DEL CLIMA:\n\n");
            body.append("• Ubicación: ").append(alert.getName()).append(", ").append(alert.getRegion()).append(" (").append(alert.getCountry()).append(")\n");
            body.append("• Temperatura actual: ").append(String.format("%.2f", alert.getTemperature())).append(" °C\n");
            body.append("• Sensación térmica: ").append(String.format("%.2f", alert.getFeelslikeC())).append(" °C\n");
            body.append("• Humedad: ").append(alert.getHumidity()).append(" %\n");
            body.append("• Condición: ").append(alert.getCondition()).append("\n");
            body.append("• Viento: ").append(alert.getWindKph()).append(" km/h\n");
            body.append("• Precipitaciones: ").append(alert.getPrecipMm()).append(" mm\n");
            body.append("• Visibilidad: ").append(alert.getVisKm()).append(" km\n");

            body.append("• Fecha/Hora de generación de alerta: ").append(nowStr).append("\n\n");

            body.append("--------------------------------------------------\n");
            body.append("Este es un mensaje automático generado por Climalert. Por favor no lo responda.");

            mail.setText(body.toString());
            mailSender.send(mail);
            alert.setState(AlertState.SENT);
        } catch (MailException e) {
            System.out.println("Error at trying to send by email: " + e.getMessage());
            e.printStackTrace();
            alert.setState(AlertState.FAILED);
        }
    }
}
