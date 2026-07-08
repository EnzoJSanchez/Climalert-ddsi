package ar.edu.utn.ba.ddsi.climalert.models.entities.notification;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MeteorologyRecipient extends Recipient{
    public MeteorologyRecipient(JavaMailSender javaMailSender) {
        super(javaMailSender, "meteorologia@clima.com");
    }


}
