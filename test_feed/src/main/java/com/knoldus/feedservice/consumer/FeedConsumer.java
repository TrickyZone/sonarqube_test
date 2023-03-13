package com.knoldus.feedservice.consumer;

import com.knoldus.feedservice.dao.FeedDataRepository;
import com.knoldus.feedservice.model.FeedData;
import com.knoldus.feedservice.util.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FeedConsumer {

    @Autowired
    private FeedDataRepository feedDataRepository;

    Logger logger = LoggerFactory.getLogger(FeedConsumer.class);

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void consumeMessageFromScoringQueue(FeedData feedData)
    {
        try {
            logger.info("Feed Received = " + feedData.toString());
            feedData.setFeedTime(new Date());
            feedDataRepository.save(feedData);
        } catch (Exception ex) {
            logger.error("Error during the Feed Processing - " + ex.getMessage() );
        }

    }
}
