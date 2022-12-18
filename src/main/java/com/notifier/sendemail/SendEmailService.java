package com.notifier.sendemail;

import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SendEmailService {

    private final JavaMailSender javaMailSender;

    public SendEmailService(final JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void send(String to, String subject, String content) {
        log.info("Sending email to " + to);

        var message = new SimpleMailMessage();
        message.setTo(to);

        message.setSubject(subject);
        message.setText(content);
        javaMailSender.send(message);
        log.info("Email sent with success!");
    }

    public void sendWithAttach(String to, String subject, String content, String file) throws MessagingException {
        log.info("Sending email to " + to);

        var mimeMessage = javaMailSender.createMimeMessage();
        var helper = new MimeMessageHelper(mimeMessage, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);

        helper.addAttachment("attachment file", new ClassPathResource(file));

        javaMailSender.send(mimeMessage);
        log.info("Email sent with success!");
    }
}
