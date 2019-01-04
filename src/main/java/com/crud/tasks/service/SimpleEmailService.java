package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;


@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleEmailService {
    private final JavaMailSender javaMailSender;

    public void send(final Mail mail){
        log.info("Starting email preparation...");
        try {
            SimpleMailMessage mailMessage = createMailMessage(mail);
            javaMailSender.send(mailMessage);

            log.info("Email has been sent.");
        } catch (MailException e) {
            log.error("Failed to process email sending: ", e.getMessage(), e);
        }
    }

    public SimpleMailMessage createMailMessage(final Mail mail){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());

        if (isValidEmailAddress(mail.getToCc())){
            mailMessage.setCc(mail.getToCc());
        }

        return mailMessage;
    }

    private boolean isValidEmailAddress(String email) {
        if (email == null) {
            log.warn("toCc is null");
            return false;
        }

        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            //log.error(ex.getMessage(), ex);
            result = false;
        }

        log.info("Field toCc validation result: " + result);
        return result;
    }

}
