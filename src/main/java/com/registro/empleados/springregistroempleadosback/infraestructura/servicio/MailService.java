package com.registro.empleados.springregistroempleadosback.infraestructura.servicio;

import com.registro.empleados.springregistroempleadosback.dominio.excepciones.CorreoActivacionException;
import com.registro.empleados.springregistroempleadosback.infraestructura.modelo.NotificacionEmail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {

    private final JavaMailSender mailSender;
    private final MailContentBuilder mailContentBuilder;

    public void sendMail(NotificacionEmail notificacionEmail) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("devfelipefranco@gmial.com");
            messageHelper.setTo(notificacionEmail.getDestinatario());
            messageHelper.setSubject(notificacionEmail.getAsunto());
            messageHelper.setText(mailContentBuilder.build(notificacionEmail.getCuerpo()));
        };

        try {
            mailSender.send(messagePreparator);
            log.info("Se envio el email para la activacion de la cuenta");
        } catch (MailException e) {
            log.error("Excepcion enviando email de activacion");
            throw new CorreoActivacionException("Ocurrio una excepcion cuando se envio el correo a " + notificacionEmail.getDestinatario(), e);
        }
    }
}
