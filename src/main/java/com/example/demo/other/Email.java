package com.example.demo.other;

import com.example.demo.config.EmailConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
public class Email {

    private final EmailConfig emailConfig;
    private Properties props;

    @Autowired
    public Email(EmailConfig emailConfig) {
        this.emailConfig = emailConfig;
    }

    @PostConstruct
    public void init() {
        this.props = new Properties();
        this.props.put("mail.smtp.auth", "true");
        this.props.put("mail.smtp.starttls.enable", "true");
        this.props.put("mail.smtp.host", "smtp.gmail.com");
        this.props.put("mail.smtp.port", "587");

        // Logging the injected values
        System.out.println("PostConstruct - EMAIL_USERNAME: " + emailConfig.getUsername());
        System.out.println("PostConstruct - EMAIL_PASSWORD: " + emailConfig.getPassword());
    }

    public void send(String para, String asunto, String mensaje) {
        System.out.println("send() - EMAIL_USERNAME: " + emailConfig.getUsername());
        System.out.println("send() - EMAIL_PASSWORD: " + emailConfig.getPassword());

        if (emailConfig.getPassword() == null || emailConfig.getUsername() == null) {
            System.out.println("Username or password is not set.");
            return;
        }

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(emailConfig.getUsername(), emailConfig.getPassword());
                    }
                });

        if (para == null) {
            return;
        }

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailConfig.getUsername()));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(para));
            message.setSubject(asunto);
            message.setText(mensaje);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
