package ar.edu.utn.ba.ddsi.climalert.models.entities.notification;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class AdminRecipient extends Recipient{
    public AdminRecipient(JavaMailSender javaMailSender) {
        super(javaMailSender, "admin@clima.com");
    }
}
