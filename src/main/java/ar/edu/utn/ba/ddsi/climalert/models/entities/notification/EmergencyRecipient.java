package ar.edu.utn.ba.ddsi.climalert.models.entities.notification;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmergencyRecipient extends Recipient{
    public EmergencyRecipient(JavaMailSender javaMailSender) {
        super(javaMailSender, "emergencias@clima.com");
    }

}
