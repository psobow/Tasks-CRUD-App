package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class SimpleEmailService {
    @Autowired
    private JavaMailSender javaMailSender;

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

    private SimpleMailMessage createMailMessage(final Mail mail){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());

        boolean isValid = isToCcFieldValid(mail);

        if(isValid) mailMessage.setCc(mail.getToCc());

        return mailMessage;
    }

    private boolean isToCcFieldValid(final Mail mail) {

        if ( mail.getToCc() == null ) {
            log.warn("Field toCc is null.");
            return false;
        }

        if (mail.getToCc() == "" ) {
            log.warn("Field toCc is empty.");
            return false;
        }


        if ( ! mail.getToCc().contains("@")){
            log.warn("Field toCc does not contain @. Email incorrect.");
            return false;
        }

        return true;
    }
}
