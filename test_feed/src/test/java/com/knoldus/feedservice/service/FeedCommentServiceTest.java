package com.knoldus.feedservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.knoldus.feedservice.dao.FeedCommentRepository;
import com.knoldus.feedservice.dao.FeedDataRepository;
import com.knoldus.feedservice.model.FeedComment;
import com.knoldus.feedservice.model.FeedData;
import com.knoldus.feedservice.model.SendEmailNotification;
import com.knoldus.feedservice.util.EmailService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.expression.ExpressionException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {FeedCommentService.class})
@ExtendWith(SpringExtension.class)
class FeedCommentServiceTest {
    @MockBean
    private EmailService emailService;

    @MockBean
    private Environment environment;

    @MockBean
    private FeedCommentRepository feedCommentRepository;

    @Autowired
    private FeedCommentService feedCommentService;

    @MockBean
    private FeedDataRepository feedDataRepository;

    /**
     * Method under test: {@link FeedCommentService#getFeedComment()}
     */
    @Test
    void testGetFeedComment() {
        ArrayList<FeedComment> feedCommentList = new ArrayList<>();
        when(feedCommentRepository.findAll()).thenReturn(feedCommentList);
        List<FeedComment> actualFeedComment = feedCommentService.getFeedComment();
        assertSame(feedCommentList, actualFeedComment);
        assertTrue(actualFeedComment.isEmpty());
        verify(feedCommentRepository).findAll();
    }

    /**
     * Method under test: {@link FeedCommentService#getFeedComment()}
     */
    @Test
    void testGetFeedComment2() {
        when(feedCommentRepository.findAll()).thenThrow(new ExpressionException("An error occurred"));
        assertThrows(ExpressionException.class, () -> feedCommentService.getFeedComment());
        verify(feedCommentRepository).findAll();
    }

    /**
     * Method under test: {@link FeedCommentService#getFeedCommentById(int)}
     */
    @Test
    void testGetFeedCommentById() {
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

        FeedComment feedComment = new FeedComment();
        feedComment.setComment("Comment");
        feedComment.setEmail("jane.doe@example.org");
        feedComment.setFeedCommentId(1);
        feedComment.setFeedData(feedData);
        feedComment.setFeedId(1);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedComment.setFeedTime(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        feedComment.setUserName("janedoe");
        Optional<FeedComment> ofResult = Optional.of(feedComment);
        when(feedCommentRepository.findById((Integer) any())).thenReturn(ofResult);
        Optional<FeedComment> actualFeedCommentById = feedCommentService.getFeedCommentById(1);
        assertSame(ofResult, actualFeedCommentById);
        assertTrue(actualFeedCommentById.isPresent());
        verify(feedCommentRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link FeedCommentService#getFeedCommentById(int)}
     */
    @Test
    void testGetFeedCommentById2() {
        when(feedCommentRepository.findById((Integer) any())).thenThrow(new ExpressionException("An error occurred"));
        assertThrows(ExpressionException.class, () -> feedCommentService.getFeedCommentById(1));
        verify(feedCommentRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link FeedCommentService#postFeedComment(int, FeedComment)}
     */
    @Test
    void testPostFeedComment() {
        doNothing().when(emailService).sendEmail((SendEmailNotification) any());
        when(environment.getProperty((String) any())).thenReturn("Property");

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

        FeedComment feedComment = new FeedComment();
        feedComment.setComment("Comment");
        feedComment.setEmail("jane.doe@example.org");
        feedComment.setFeedCommentId(1);
        feedComment.setFeedData(feedData);
        feedComment.setFeedId(1);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedComment.setFeedTime(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        feedComment.setUserName("janedoe");
        when(feedCommentRepository.save((FeedComment) any())).thenReturn(feedComment);

        FeedData feedData1 = new FeedData();
        feedData1.setContributionId("42");
        feedData1.setContributionType("Contribution Type");
        feedData1.setDescription("The characteristics of someone or something");
        feedData1.setEmail("jane.doe@example.org");
        feedData1.setFeedComments(new ArrayList<>());
        feedData1.setFeedId(1);
        feedData1.setFeedLike(new ArrayList<>());
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedData1.setFeedTime(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        feedData1.setFeedType("Feed Type");
        feedData1.setLapseTimeDifference("Lapse Time Difference");
        feedData1.setTenantId("42");
        feedData1.setTitle("Dr");
        feedData1.setUrl("https://example.org/example");
        feedData1.setUserName("janedoe");
        feedData1.setUserXref(1);
        Optional<FeedData> ofResult = Optional.of(feedData1);
        when(feedDataRepository.findById((Integer) any())).thenReturn(ofResult);

        FeedData feedData2 = new FeedData();
        feedData2.setContributionId("42");
        feedData2.setContributionType("Contribution Type");
        feedData2.setDescription("The characteristics of someone or something");
        feedData2.setEmail("jane.doe@example.org");
        feedData2.setFeedComments(new ArrayList<>());
        feedData2.setFeedId(1);
        feedData2.setFeedLike(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedData2.setFeedTime(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        feedData2.setFeedType("Feed Type");
        feedData2.setLapseTimeDifference("Lapse Time Difference");
        feedData2.setTenantId("42");
        feedData2.setTitle("Dr");
        feedData2.setUrl("https://example.org/example");
        feedData2.setUserName("janedoe");
        feedData2.setUserXref(1);

        FeedComment feedComment1 = new FeedComment();
        feedComment1.setComment("Comment");
        feedComment1.setEmail("jane.doe@example.org");
        feedComment1.setFeedCommentId(1);
        feedComment1.setFeedData(feedData2);
        feedComment1.setFeedId(1);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedComment1.setFeedTime(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        feedComment1.setUserName("janedoe");
        assertSame(feedComment, feedCommentService.postFeedComment(1, feedComment1));
        verify(emailService).sendEmail((SendEmailNotification) any());
        verify(environment, atLeast(1)).getProperty((String) any());
        verify(feedCommentRepository).save((FeedComment) any());
        verify(feedDataRepository, atLeast(1)).findById((Integer) any());
        assertEquals(feedData2, feedComment1.getFeedData());
    }

    /**
     * Method under test: {@link FeedCommentService#postFeedComment(int, FeedComment)}
     */
    @Test
    void testPostFeedComment2() {
        doNothing().when(emailService).sendEmail((SendEmailNotification) any());
        when(environment.getProperty((String) any())).thenReturn("Property");
        when(feedCommentRepository.save((FeedComment) any())).thenThrow(new ExpressionException("An error occurred"));

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
        Optional<FeedData> ofResult = Optional.of(feedData);
        when(feedDataRepository.findById((Integer) any())).thenReturn(ofResult);

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

        FeedComment feedComment = new FeedComment();
        feedComment.setComment("Comment");
        feedComment.setEmail("jane.doe@example.org");
        feedComment.setFeedCommentId(1);
        feedComment.setFeedData(feedData1);
        feedComment.setFeedId(1);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedComment.setFeedTime(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        feedComment.setUserName("janedoe");
        assertThrows(ExpressionException.class, () -> feedCommentService.postFeedComment(1, feedComment));
        verify(feedCommentRepository).save((FeedComment) any());
        verify(feedDataRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link FeedCommentService#deleteFeedComment()}
     */
    @Test
    void testDeleteFeedComment() {
        doNothing().when(feedCommentRepository).deleteAll();
        feedCommentService.deleteFeedComment();
        verify(feedCommentRepository).deleteAll();
        assertTrue(feedCommentService.getFeedComment().isEmpty());
    }

    /**
     * Method under test: {@link FeedCommentService#deleteFeedComment()}
     */
    @Test
    void testDeleteFeedComment2() {
        doThrow(new ExpressionException("An error occurred")).when(feedCommentRepository).deleteAll();
        assertThrows(ExpressionException.class, () -> feedCommentService.deleteFeedComment());
        verify(feedCommentRepository).deleteAll();
    }

    /**
     * Method under test: {@link FeedCommentService#updateFeedComment(int, FeedComment)}
     */
    @Test
    void testUpdateFeedComment() {
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

        FeedComment feedComment = new FeedComment();
        feedComment.setComment("Comment");
        feedComment.setEmail("jane.doe@example.org");
        feedComment.setFeedCommentId(1);
        feedComment.setFeedData(feedData);
        feedComment.setFeedId(1);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedComment.setFeedTime(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        feedComment.setUserName("janedoe");
        Optional<FeedComment> ofResult = Optional.of(feedComment);

        FeedData feedData1 = new FeedData();
        feedData1.setContributionId("42");
        feedData1.setContributionType("Contribution Type");
        feedData1.setDescription("The characteristics of someone or something");
        feedData1.setEmail("jane.doe@example.org");
        feedData1.setFeedComments(new ArrayList<>());
        feedData1.setFeedId(1);
        feedData1.setFeedLike(new ArrayList<>());
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedData1.setFeedTime(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        feedData1.setFeedType("Feed Type");
        feedData1.setLapseTimeDifference("Lapse Time Difference");
        feedData1.setTenantId("42");
        feedData1.setTitle("Dr");
        feedData1.setUrl("https://example.org/example");
        feedData1.setUserName("janedoe");
        feedData1.setUserXref(1);

        FeedComment feedComment1 = new FeedComment();
        feedComment1.setComment("Comment");
        feedComment1.setEmail("jane.doe@example.org");
        feedComment1.setFeedCommentId(1);
        feedComment1.setFeedData(feedData1);
        feedComment1.setFeedId(1);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedComment1.setFeedTime(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        feedComment1.setUserName("janedoe");
        when(feedCommentRepository.save((FeedComment) any())).thenReturn(feedComment1);
        when(feedCommentRepository.findById((Integer) any())).thenReturn(ofResult);

        FeedData feedData2 = new FeedData();
        feedData2.setContributionId("42");
        feedData2.setContributionType("Contribution Type");
        feedData2.setDescription("The characteristics of someone or something");
        feedData2.setEmail("jane.doe@example.org");
        feedData2.setFeedComments(new ArrayList<>());
        feedData2.setFeedId(1);
        feedData2.setFeedLike(new ArrayList<>());
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedData2.setFeedTime(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        feedData2.setFeedType("Feed Type");
        feedData2.setLapseTimeDifference("Lapse Time Difference");
        feedData2.setTenantId("42");
        feedData2.setTitle("Dr");
        feedData2.setUrl("https://example.org/example");
        feedData2.setUserName("janedoe");
        feedData2.setUserXref(1);

        FeedComment feedComment2 = new FeedComment();
        feedComment2.setComment("Comment");
        feedComment2.setEmail("jane.doe@example.org");
        feedComment2.setFeedCommentId(1);
        feedComment2.setFeedData(feedData2);
        feedComment2.setFeedId(1);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedComment2.setFeedTime(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        feedComment2.setUserName("janedoe");
        Optional<FeedComment> actualUpdateFeedCommentResult = feedCommentService.updateFeedComment(1, feedComment2);
        assertSame(ofResult, actualUpdateFeedCommentResult);
        assertTrue(actualUpdateFeedCommentResult.isPresent());
        verify(feedCommentRepository).save((FeedComment) any());
        verify(feedCommentRepository, atLeast(1)).findById((Integer) any());
    }

    /**
     * Method under test: {@link FeedCommentService#updateFeedComment(int, FeedComment)}
     */
    @Test
    void testUpdateFeedComment2() {
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

        FeedComment feedComment = new FeedComment();
        feedComment.setComment("Comment");
        feedComment.setEmail("jane.doe@example.org");
        feedComment.setFeedCommentId(1);
        feedComment.setFeedData(feedData);
        feedComment.setFeedId(1);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedComment.setFeedTime(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        feedComment.setUserName("janedoe");
        Optional<FeedComment> ofResult = Optional.of(feedComment);
        when(feedCommentRepository.save((FeedComment) any())).thenThrow(new ExpressionException("An error occurred"));
        when(feedCommentRepository.findById((Integer) any())).thenReturn(ofResult);

        FeedData feedData1 = new FeedData();
        feedData1.setContributionId("42");
        feedData1.setContributionType("Contribution Type");
        feedData1.setDescription("The characteristics of someone or something");
        feedData1.setEmail("jane.doe@example.org");
        feedData1.setFeedComments(new ArrayList<>());
        feedData1.setFeedId(1);
        feedData1.setFeedLike(new ArrayList<>());
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedData1.setFeedTime(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        feedData1.setFeedType("Feed Type");
        feedData1.setLapseTimeDifference("Lapse Time Difference");
        feedData1.setTenantId("42");
        feedData1.setTitle("Dr");
        feedData1.setUrl("https://example.org/example");
        feedData1.setUserName("janedoe");
        feedData1.setUserXref(1);

        FeedComment feedComment1 = new FeedComment();
        feedComment1.setComment("Comment");
        feedComment1.setEmail("jane.doe@example.org");
        feedComment1.setFeedCommentId(1);
        feedComment1.setFeedData(feedData1);
        feedComment1.setFeedId(1);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedComment1.setFeedTime(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        feedComment1.setUserName("janedoe");
        assertThrows(ExpressionException.class, () -> feedCommentService.updateFeedComment(1, feedComment1));
        verify(feedCommentRepository).save((FeedComment) any());
        verify(feedCommentRepository).findById((Integer) any());
    }
}

