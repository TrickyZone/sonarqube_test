package com.knoldus.feedservice.util;

import com.knoldus.feedservice.model.SendEmailNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmailService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private Environment environment;

    Logger logger = LoggerFactory.getLogger(EmailService.class);


    public void sendEmail(SendEmailNotification sendEmailNotification) {

        try {
            String emailServiceURL = environment.getProperty("notificationServiceUrl");
            String sendGridApiKey = environment.getProperty("sendGridApiKey");
            HttpHeaders headers = new HttpHeaders();
            headers.set("SENDGRID_API_KEY", sendGridApiKey);
            HttpEntity<SendEmailNotification> request = new HttpEntity<>(sendEmailNotification, headers);
            restTemplate.postForObject(emailServiceURL, request, SendEmailNotification.class);
        } catch (Exception ex) {
            logger.error("Email Notification Failed", ex);
        }

    }
}
