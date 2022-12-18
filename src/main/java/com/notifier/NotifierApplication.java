package com.notifier;

import com.notifier.websites.nike.brazilshirt.VerifyBrazilShirt;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.mail.MessagingException;

@SpringBootApplication
public class NotifierApplication implements CommandLineRunner {

    private VerifyBrazilShirt verifyBrazilShirt;

    public NotifierApplication(VerifyBrazilShirt verifyBrazilShirt) {
        this.verifyBrazilShirt = verifyBrazilShirt;
    }

    public static void main(String[] args) {
        SpringApplication.run(NotifierApplication.class, args);
    }

    @Override
    public void run(String... args) throws MessagingException {
        verifyBrazilShirt.run();
    }
}