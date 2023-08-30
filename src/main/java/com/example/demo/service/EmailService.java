package com.example.demo.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    
    @Autowired
    private JavaMailSender mailSender;

       public void enviarCorreo(String destinatario, String asunto, String cuerpo) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(destinatario);
        helper.setSubject(asunto);
        helper.setText(cuerpo, true);
        mailSender.send(message);
    }

     public void enviarCorreoConAdjunto(String destinatario, String asunto, String cuerpo, Object object, String name) {
        MimeMessage mensaje = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mensaje, true);
            
            helper.setTo(destinatario);
            helper.setSubject(asunto);
            helper.setText(cuerpo);

        InputStreamSource fuenteAdjunta = new ByteArrayResource((byte[]) object);
        helper.addAttachment(name+".csv",fuenteAdjunta);

            mailSender.send(mensaje);
        } catch (MessagingException e) {
            e.printStackTrace();
            
        }
    }

    public void enviarCorreoConAdjunto2(String destinatario, String asunto, String cuerpo, Object csvFunction) {
        MimeMessage mensaje = mailSender.createMimeMessage();
          try {
            MimeMessageHelper helper = new MimeMessageHelper(mensaje, true);
            
            helper.setFrom("cgaetej.srv@ips.gob.cl");
            helper.setTo(destinatario);
            helper.setSubject(asunto);
            helper.setText(cuerpo);

        InputStreamSource fuenteAdjunta = new ByteArrayResource((byte[]) csvFunction);
        helper.addAttachment("prueba.pdf", fuenteAdjunta);

            mailSender.send(mensaje);
        } catch (MessagingException e) {
            e.printStackTrace();
            
        }
    }
}
