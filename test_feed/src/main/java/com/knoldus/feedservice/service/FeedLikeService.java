package com.knoldus.feedservice.service;

import com.knoldus.feedservice.dao.FeedDataRepository;
import com.knoldus.feedservice.dao.FeedLikeRepository;
import com.knoldus.feedservice.model.FeedData;
import com.knoldus.feedservice.model.FeedLike;
import com.knoldus.feedservice.model.SendEmailNotification;
import com.knoldus.feedservice.util.EmailService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.knoldus.feedservice.util.Constants.PAGE_SIZE_KEY;

@Service
public class FeedLikeService {

    @Autowired
    FeedLikeRepository feedLikeRepository;

    @Autowired
    private FeedDataRepository feedDataRepository;

    @Autowired
    Environment environment;

    @Autowired
    private EmailService emailService;

    public List<FeedLike> getFeedLike() {
        return (List<FeedLike>) feedLikeRepository.findAll();
    }

    @Transactional
    public FeedLike postFeedLike(int feedId, FeedLike feedLike) {

        Optional<FeedData> optFeedData = feedDataRepository.findById(feedId);
        if (optFeedData.isPresent()) {
            return updateFeedLike(feedLike, optFeedData, Collections.singletonList(optFeedData.get().getEmail()));
        }
        return null;
    }

    private FeedLike updateFeedLike(FeedLike feedLike, Optional<FeedData> optFeedData, List<String> recipientEmailList) {
        List<FeedLike> feedFromDB = optFeedData.get().getFeedLike().stream()
                .filter(e -> e.getEmail().equals(feedLike.getEmail())).collect(Collectors.toList());
        if (!feedFromDB.isEmpty()) {
            FeedLike getData = feedFromDB.get(0);
            if ((feedLike.getLikes() != null && getData.getLikes() != null
                    && getData.getLikes() && feedLike.getLikes())
                    || (getData.getDislike() != null && feedLike.getDislike() != null
                    && getData.getDislike() && feedLike.getDislike())) {

                feedLikeRepository.delete(getData);
                return feedLike;
            } else if ((feedLike.getDislike() != null && getData.getLikes() != null
                    && (getData.getLikes() && feedLike.getDislike()))
                    || (getData.getDislike() != null && feedLike.getLikes() != null
                    && (getData.getDislike() && feedLike.getLikes()))) {
                getData.setLikes(feedLike.getLikes());
                getData.setDislike(feedLike.getDislike());
                feedLikeRepository.save(getData);
                sendEmail(feedLike, recipientEmailList);
                return getData;
            }


        }
        return saveFeedLike(feedLike, optFeedData, recipientEmailList);
    }

    private FeedLike saveFeedLike(FeedLike feedLike, Optional<FeedData> optFeedData, List<String> recipientEmailList) {
        return optFeedData.map(feedData -> {
            feedLike.setFeedData(feedData);
            sendEmail(feedLike, recipientEmailList);
            return feedLikeRepository.save(feedLike);
        }).orElseThrow(() -> new ExpressionException("FeedId not found"));
    }

    private void sendEmail(FeedLike feedLike, List<String> recipientEmailList) {
        String emailMessage = environment.getProperty("sendEmailLike");
        if (feedLike.getDislike() != null && true == feedLike.getDislike()) {
            emailMessage = environment.getProperty("sendEmailDislike");
        }
        SendEmailNotification sendEmailNotification = new SendEmailNotification(recipientEmailList,
                environment.getProperty("sender"),
                feedLike.getUserName() + " " + environment.getProperty("httpTitle"),
                emailMessage);
        emailService.sendEmail(sendEmailNotification);
    }

    public void deleteFeedLike() {
        feedLikeRepository.deleteAll();
        System.out.println("Successfully deleted all tables");
    }

    public FeedLike updateFeedLike(int id, FeedLike feedLike) {
        FeedLike feedLikeRepositoryById = feedLikeRepository.findById(id).orElseThrow();
        if (feedLikeRepositoryById != null) {
            feedLikeRepositoryById.setLikes(feedLike.getLikes());
            feedLikeRepositoryById.setDislike(feedLike.getDislike());
            feedLikeRepository.save(feedLikeRepositoryById);
        }
        return feedLikeRepositoryById;
    }

    public List<FeedLike> displayAllLikeUsername(int pageNo, int feedId) {
        int pageSize = Integer.parseInt(environment.getProperty(PAGE_SIZE_KEY));
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<FeedLike> usernameList = feedLikeRepository.findUserNameForLikesByFeedId(pageable, feedId);
        return usernameList.toList();
    }

    public List<FeedLike> displayAllDisLikeUsername(int pageNo, int feedId) {
        int pageSize = Integer.parseInt(environment.getProperty(PAGE_SIZE_KEY));
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<FeedLike> usernameList = feedLikeRepository.findUserNameForDisLikesByFeedId(pageable, feedId);
        return usernameList.toList();
    }
}
