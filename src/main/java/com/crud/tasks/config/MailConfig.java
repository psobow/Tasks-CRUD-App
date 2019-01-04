package com.crud.tasks.config;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class MailConfig {
    @Value("${spring.mail.username}")
    private String mailUsername;
    @Value("${spring.mail.password}")
    private String mailPassword;

}
