package com.knoldus.feedservice.service;

import com.knoldus.feedservice.dao.FeedCommentRepository;
import com.knoldus.feedservice.dao.FeedDataRepository;
import com.knoldus.feedservice.model.FeedComment;
import com.knoldus.feedservice.model.SendEmailNotification;
import com.knoldus.feedservice.util.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class FeedCommentService {

    @Autowired
    private FeedCommentRepository feedCommentRepository;


    @Autowired
    private FeedDataRepository feedDataRepository;

    @Autowired
    private Environment environment;

    @Autowired
    private EmailService emailService;

    public List<FeedComment> getFeedComment() {
        return (List<FeedComment>) feedCommentRepository.findAll();
    }

    public Optional<FeedComment> getFeedCommentById(int feedId) {
        return feedCommentRepository.findById(feedId);
    }

    public FeedComment postFeedComment(int id, FeedComment feedComment) {
        return feedDataRepository.findById(id).map(Comment -> {
            feedComment.setFeedData(Comment);
            FeedComment updatedFeedComment = feedCommentRepository.save(feedComment);
            String recipientEmail = feedDataRepository.findById(id).get().getEmail();
            List<String> recipientEmailList = new ArrayList<>();
            recipientEmailList.add(recipientEmail);
            SendEmailNotification sendEmailNotification = new SendEmailNotification(recipientEmailList,
                    environment.getProperty("sender"),
                    feedComment.getUserName() + " " + environment.getProperty("httpTitle"), feedComment.getComment());
            emailService.sendEmail(sendEmailNotification);
            return updatedFeedComment;
        }).orElseThrow(() -> new ExpressionException("FeedId not found"));
    }

    public void deleteFeedComment() {
        feedCommentRepository.deleteAll();
    }

    public Optional<FeedComment> updateFeedComment(int id, FeedComment feedComment) {
        Optional<FeedComment> feedLikeRepositoryById = feedCommentRepository.findById(id);
        feedLikeRepositoryById.ifPresent(b -> b.setEmail(feedComment.getEmail()));
        feedLikeRepositoryById.ifPresent(b -> b.setComment(feedComment.getComment()));
        feedLikeRepositoryById.ifPresent(b -> feedCommentRepository.save(b));
        return feedCommentRepository.findById(id);
    }
}
