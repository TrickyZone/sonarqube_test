package com.knoldus.feedservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendEmailNotification {
    private List<String> recipients;
    private String sender;
    private String htmlTitle;
    private String htmlBody;
}
