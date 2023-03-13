package com.knoldus.feedservice.util;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.knoldus.feedservice.model.SendEmailNotification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EmailService.class})
@ExtendWith(SpringExtension.class)
class EmailServiceTest {
    @Autowired
    private EmailService emailService;

    @MockBean
    private Environment environment;

    /**
     * Method under test: {@link EmailService#sendEmail(SendEmailNotification)}
     */
    @Test
    void testSendEmail() {
        when(environment.getProperty((String) any())).thenReturn("Property");
        emailService.sendEmail(new SendEmailNotification());
        verify(environment, atLeast(1)).getProperty((String) any());
    }
}

