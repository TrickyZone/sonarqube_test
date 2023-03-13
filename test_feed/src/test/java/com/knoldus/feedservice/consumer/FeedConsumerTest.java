package com.knoldus.feedservice.consumer;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.knoldus.feedservice.dao.FeedDataRepository;
import com.knoldus.feedservice.model.FeedComment;
import com.knoldus.feedservice.model.FeedData;
import com.knoldus.feedservice.model.FeedLike;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {FeedConsumer.class})
@ExtendWith(SpringExtension.class)
class FeedConsumerTest {
    @Autowired
    private FeedConsumer feedConsumer;

    @MockBean
    private FeedDataRepository feedDataRepository;

    /**
     * Method under test: {@link FeedConsumer#consumeMessageFromScoringQueue(FeedData)}
     */
    @Test
    void testConsumeMessageFromScoringQueue() {
        FeedData feedData = new FeedData();
        feedData.setContributionId("42");
        feedData.setContributionType("Contribution Type");
        feedData.setDescription("The characteristics of someone or something");
        feedData.setEmail("jane.doe@example.org");
        feedData.setFeedComments(new ArrayList<>());
        feedData.setFeedId(123);
        feedData.setFeedLike(new ArrayList<>());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedData.setFeedTime(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        feedData.setFeedType("Feed Type");
        feedData.setLapseTimeDifference("Lapse Time Difference");
        feedData.setTenantId("42");
        feedData.setTitle("Dr");
        feedData.setUrl("https://example.org/example");
        feedData.setUserName("janedoe");
        feedData.setUserXref(1);
        when(feedDataRepository.save((FeedData) any())).thenReturn(feedData);

        FeedData feedData1 = new FeedData();
        feedData1.setContributionId("42");
        feedData1.setContributionType("Contribution Type");
        feedData1.setDescription("The characteristics of someone or something");
        feedData1.setEmail("jane.doe@example.org");
        feedData1.setFeedComments(new ArrayList<>());
        feedData1.setFeedId(123);
        feedData1.setFeedLike(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedData1.setFeedTime(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        feedData1.setFeedType("Feed Type");
        feedData1.setLapseTimeDifference("Lapse Time Difference");
        feedData1.setTenantId("42");
        feedData1.setTitle("Dr");
        feedData1.setUrl("https://example.org/example");
        feedData1.setUserName("janedoe");
        feedData1.setUserXref(1);
        feedConsumer.consumeMessageFromScoringQueue(feedData1);
        verify(feedDataRepository).save((FeedData) any());
    }

    /**
     * Method under test: {@link FeedConsumer#consumeMessageFromScoringQueue(FeedData)}
     */
    @Test
    void testConsumeMessageFromScoringQueue2() {
        FeedData feedData = new FeedData();
        feedData.setContributionId("42");
        feedData.setContributionType("Contribution Type");
        feedData.setDescription("The characteristics of someone or something");
        feedData.setEmail("jane.doe@example.org");
        feedData.setFeedComments(new ArrayList<>());
        feedData.setFeedId(1);
        feedData.setFeedLike(new ArrayList<>());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedData.setFeedTime(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        feedData.setFeedType("Feed Type");
        feedData.setLapseTimeDifference("Lapse Time Difference");
        feedData.setTenantId("42");
        feedData.setTitle("Dr");
        feedData.setUrl("https://example.org/example");
        feedData.setUserName("janedoe");
        feedData.setUserXref(1);
        when(feedDataRepository.save((FeedData) any())).thenReturn(feedData);

        FeedData feedData1 = new FeedData();
        feedData1.setContributionId("42");
        feedData1.setContributionType("Contribution Type");
        feedData1.setDescription("The characteristics of someone or something");
        feedData1.setEmail("jane.doe@example.org");
        feedData1.setFeedComments(new ArrayList<>());
        feedData1.setFeedId(1);
        feedData1.setFeedLike(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedData1.setFeedTime(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        feedData1.setFeedType("Feed Type");
        feedData1.setLapseTimeDifference("Lapse Time Difference");
        feedData1.setTenantId("42");
        feedData1.setTitle("Dr");
        feedData1.setUrl("https://example.org/example");
        feedData1.setUserName("janedoe");
        feedData1.setUserXref(1);
        feedConsumer.consumeMessageFromScoringQueue(feedData1);
        verify(feedDataRepository).save((FeedData) any());
    }
}

