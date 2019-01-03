package com.crud.tasks.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailConfigTest {

    @Autowired
    MailConfig mailConfig;

    @Test
    public void printGmailValues(){
        System.out.println("username: " + mailConfig.getMailUsername() );
        System.out.println("Password: " + mailConfig.getMailPassword() );
    }
}