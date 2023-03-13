package com.knoldus.feedservice.service;

import com.knoldus.feedservice.dao.FeedDataRepository;
import com.knoldus.feedservice.dao.FeedLikeRepository;
import com.knoldus.feedservice.model.FeedData;
import com.knoldus.feedservice.model.FeedLike;
import com.knoldus.feedservice.model.SendEmailNotification;
import com.knoldus.feedservice.util.EmailService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.expression.ExpressionException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {FeedLikeService.class, EmailService.class})
@ExtendWith(SpringExtension.class)
class FeedLikeServiceTest {
    @MockBean
    private EmailService emailService;

    @MockBean
    private Environment environment;

    @MockBean
    private FeedDataRepository feedDataRepository;

    @MockBean
    private FeedLikeRepository feedLikeRepository;

    @Autowired
    private FeedLikeService feedLikeService;

    /**
     * Method under test: {@link FeedLikeService#getFeedLike()}
     */
    @Test
    void testGetFeedLike() {
        ArrayList<FeedLike> feedLikeList = new ArrayList<>();
        when(this.feedLikeRepository.findAll()).thenReturn(feedLikeList);
        List<FeedLike> actualFeedLike = this.feedLikeService.getFeedLike();
        assertSame(feedLikeList, actualFeedLike);
        assertTrue(actualFeedLike.isEmpty());
        verify(this.feedLikeRepository).findAll();
    }

    /**
     * Method under test: {@link FeedLikeService#getFeedLike()}
     */
    @Test
    void testGetFeedLike2() {
        when(this.feedLikeRepository.findAll()).thenThrow(new ExpressionException("An error occurred"));
        assertThrows(ExpressionException.class, () -> this.feedLikeService.getFeedLike());
        verify(this.feedLikeRepository).findAll();
    }

    /**
     * Method under test: {@link FeedLikeService#getFeedLike()}
     */
    @Test
    void testGetFeedLike3() {
        ArrayList<FeedLike> feedLikeList = new ArrayList<>();
        when(feedLikeRepository.findAll()).thenReturn(feedLikeList);
        List<FeedLike> actualFeedLike = feedLikeService.getFeedLike();
        assertSame(feedLikeList, actualFeedLike);
        assertTrue(actualFeedLike.isEmpty());
        verify(feedLikeRepository).findAll();
    }

    /**
     * Method under test: {@link FeedLikeService#getFeedLike()}
     */
    @Test
    void testGetFeedLike4() {
        when(feedLikeRepository.findAll()).thenThrow(new ExpressionException("An error occurred"));
        assertThrows(ExpressionException.class, () -> feedLikeService.getFeedLike());
        verify(feedLikeRepository).findAll();
    }

    /**
     * Method under test: {@link FeedLikeService#postFeedLike(int, FeedLike)}
     */
    @Test
    void testPostFeedLike() {
        FeedData feedData = new FeedData();
        feedData.setContributionId("123");
        feedData.setContributionType("Contribution Type");
        feedData.setDescription("The characteristics of someone or something");
        feedData.setEmail("jane.doe@example.org");
        feedData.setFeedComments(new ArrayList<>());
        feedData.setFeedId(123);
        feedData.setFeedLike(new ArrayList<>());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedData.setFeedTime(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        feedData.setTitle("Dr");
        feedData.setUrl("https://example.org/example");
        feedData.setUserName("janedoe");

        FeedLike feedLike = new FeedLike();
        feedLike.setDislike(true);
        feedLike.setEmail("jane.doe@example.org");
        feedLike.setFeedData(feedData);
        feedLike.setFeedId(123);
        feedLike.setFeedLikeId(123);
        feedLike.setLikes(true);
        feedLike.setUserName("janedoe");
        doNothing().when(this.feedLikeRepository).delete((FeedLike) any());
        when(this.feedLikeRepository.findByEmailAndFeedId((String) any(), anyInt())).thenReturn(feedLike);

        FeedData feedData1 = new FeedData();
        feedData1.setContributionId("123");
        feedData1.setContributionType("Contribution Type");
        feedData1.setDescription("The characteristics of someone or something");
        feedData1.setEmail("jane.doe@example.org");
        feedData1.setFeedComments(new ArrayList<>());
        feedData1.setFeedId(123);
        feedData1.setFeedLike(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedData1.setFeedTime(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        feedData1.setTitle("Dr");
        feedData1.setUrl("https://example.org/example");
        feedData1.setUserName("janedoe");

        FeedLike feedLike1 = new FeedLike();
        feedLike1.setDislike(true);
        feedLike1.setEmail("jane.doe@example.org");
        feedLike1.setFeedData(feedData1);
        feedLike1.setFeedId(123);
        feedLike1.setFeedLikeId(123);
        feedLike1.setLikes(true);
        feedLike1.setUserName("janedoe");
        when(this.feedLikeRepository.save(any())).thenReturn(feedLike1);
        when(this.feedDataRepository.findById(anyInt())).thenReturn(Optional.of(feedData1));
        assertSame(feedLike1, this.feedLikeService.postFeedLike(123, feedLike1));
        verify(this.feedLikeRepository).save((FeedLike) any());
    }

    /**
     * Method under test: {@link FeedLikeService#postFeedLike(int, FeedLike)}
     */
    @Test
    void testPostFeedLike2() {
        FeedData feedData = new FeedData();
        feedData.setContributionId("123");
        feedData.setContributionType("Contribution Type");
        feedData.setDescription("The characteristics of someone or something");
        feedData.setEmail("jane.doe@example.org");
        feedData.setFeedComments(new ArrayList<>());
        feedData.setFeedId(123);
        feedData.setFeedLike(new ArrayList<>());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedData.setFeedTime(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        feedData.setTitle("Dr");
        feedData.setUrl("https://example.org/example");
        feedData.setUserName("janedoe");

        FeedLike feedLike = new FeedLike();
        feedLike.setDislike(true);
        feedLike.setEmail("jane.doe@example.org");
        feedLike.setFeedData(feedData);
        feedLike.setFeedId(123);
        feedLike.setFeedLikeId(123);
        feedLike.setLikes(true);
        feedLike.setUserName("janedoe");
        doNothing().when(this.feedLikeRepository).delete((FeedLike) any());
        when(this.feedLikeRepository.findByEmailAndFeedId((String) any(), anyInt())).thenReturn(feedLike);


        FeedData feedData1 = new FeedData();
        feedData1.setContributionId("123");
        feedData1.setContributionType("Contribution Type");
        feedData1.setDescription("The characteristics of someone or something");
        feedData1.setEmail("jane.doe@example.org");
        feedData1.setFeedComments(new ArrayList<>());
        feedData1.setFeedId(123);
        feedData1.setFeedLike(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedData1.setFeedTime(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        feedData1.setTitle("Dr");
        feedData1.setUrl("https://example.org/example");
        feedData1.setUserName("janedoe");

        FeedLike feedLike1 = new FeedLike();
        feedLike1.setDislike(true);
        feedLike1.setEmail("jane.doe@example.org");
        feedLike1.setFeedData(feedData1);
        feedLike1.setFeedId(123);
        feedLike1.setFeedLikeId(123);
        feedLike1.setLikes(true);
        feedLike1.setUserName("janedoe");
        when(this.feedLikeRepository.save(any())).thenReturn(feedLike1);
        when(this.feedDataRepository.findById(anyInt())).thenReturn(Optional.of(feedData1));
        assertSame(feedLike1, this.feedLikeService.postFeedLike(123, feedLike1));
        verify(this.feedLikeRepository).save((FeedLike) any());
    }

    /**
     * Method under test: {@link FeedLikeService#postFeedLike(int, FeedLike)}
     */
    @Test
    void testPostFeedLike3() {
        doNothing().when(emailService).sendEmail((SendEmailNotification) any());
        when(environment.getProperty((String) any())).thenReturn("Property");

        FeedData feedData = new FeedData();
        feedData.setContributionId("42");
        feedData.setContributionType("sendEmailLike");
        feedData.setDescription("The characteristics of someone or something");
        feedData.setEmail("jane.doe@example.org");
        feedData.setFeedComments(new ArrayList<>());
        feedData.setFeedId(123);
        feedData.setFeedLike(new ArrayList<>());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedData.setFeedTime(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        feedData.setFeedType("sendEmailLike");
        feedData.setLapseTimeDifference("sendEmailLike");
        feedData.setTenantId("42");
        feedData.setTitle("Dr");
        feedData.setUrl("https://example.org/example");
        feedData.setUserName("janedoe");
        feedData.setUserXref(1);
        FeedLike feedLike = mock(FeedLike.class);
        when(feedLike.getDislike()).thenReturn(true);
        when(feedLike.getLikes()).thenReturn(false);
        when(feedLike.getEmail()).thenReturn("jane.doe@example.org");
        doNothing().when(feedLike).setDislike((Boolean) any());
        doNothing().when(feedLike).setEmail((String) any());
        doNothing().when(feedLike).setFeedData((FeedData) any());
        doNothing().when(feedLike).setFeedId(anyInt());
        doNothing().when(feedLike).setFeedLikeId(anyInt());
        doNothing().when(feedLike).setLikes((Boolean) any());
        doNothing().when(feedLike).setUserName((String) any());
        feedLike.setDislike(true);
        feedLike.setEmail("jane.doe@example.org");
        feedLike.setFeedData(feedData);
        feedLike.setFeedId(123);
        feedLike.setFeedLikeId(123);
        feedLike.setLikes(true);
        feedLike.setUserName("janedoe");

        ArrayList<FeedLike> feedLikeList = new ArrayList<>();
        feedLikeList.add(feedLike);

        FeedData feedData1 = new FeedData();
        feedData1.setContributionId("42");
        feedData1.setContributionType("Contribution Type");
        feedData1.setDescription("The characteristics of someone or something");
        feedData1.setEmail("jane.doe@example.org");
        feedData1.setFeedComments(new ArrayList<>());
        feedData1.setFeedId(123);
        feedData1.setFeedLike(feedLikeList);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedData1.setFeedTime(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
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
        feedData2.setFeedId(123);
        feedData2.setFeedLike(new ArrayList<>());
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedData2.setFeedTime(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        feedData2.setFeedType("Feed Type");
        feedData2.setLapseTimeDifference("Lapse Time Difference");
        feedData2.setTenantId("42");
        feedData2.setTitle("Dr");
        feedData2.setUrl("https://example.org/example");
        feedData2.setUserName("janedoe");
        feedData2.setUserXref(1);

        FeedLike feedLike1 = new FeedLike();
        feedLike1.setDislike(true);
        feedLike1.setEmail("jane.doe@example.org");
        feedLike1.setFeedData(feedData2);
        feedLike1.setFeedId(123);
        feedLike1.setFeedLikeId(123);
        feedLike1.setLikes(true);
        feedLike1.setUserName("janedoe");
        doNothing().when(feedLikeRepository).delete((FeedLike) any());
        when(feedLikeRepository.save((FeedLike) any())).thenReturn(feedLike1);

        FeedData feedData3 = new FeedData();
        feedData3.setContributionId("42");
        feedData3.setContributionType("Contribution Type");
        feedData3.setDescription("The characteristics of someone or something");
        feedData3.setEmail("jane.doe@example.org");
        feedData3.setFeedComments(new ArrayList<>());
        feedData3.setFeedId(123);
        feedData3.setFeedLike(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedData3.setFeedTime(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        feedData3.setFeedType("Feed Type");
        feedData3.setLapseTimeDifference("Lapse Time Difference");
        feedData3.setTenantId("42");
        feedData3.setTitle("Dr");
        feedData3.setUrl("https://example.org/example");
        feedData3.setUserName("janedoe");
        feedData3.setUserXref(1);

        FeedLike feedLike2 = new FeedLike();
        feedLike2.setDislike(true);
        feedLike2.setEmail("jane.doe@example.org");
        feedLike2.setFeedData(feedData3);
        feedLike2.setFeedId(123);
        feedLike2.setFeedLikeId(123);
        feedLike2.setLikes(true);
        feedLike2.setUserName("janedoe");
        assertSame(feedLike2, feedLikeService.postFeedLike(123, feedLike2));
        verify(feedDataRepository).findById((Integer) any());
        verify(feedLike, atLeast(1)).getDislike();
        verify(feedLike, atLeast(1)).getLikes();
        verify(feedLike).getEmail();
        verify(feedLike).setDislike((Boolean) any());
        verify(feedLike).setEmail((String) any());
        verify(feedLike).setFeedData((FeedData) any());
        verify(feedLike).setFeedId(anyInt());
        verify(feedLike).setFeedLikeId(anyInt());
        verify(feedLike).setLikes((Boolean) any());
        verify(feedLike).setUserName((String) any());
        verify(feedLikeRepository).delete((FeedLike) any());
    }





    /**
     * Method under test: {@link FeedLikeService#postFeedLike(int, FeedLike)}
     */
    @Test
    void testPostFeedLike6() {
        doNothing().when(emailService).sendEmail((SendEmailNotification) any());
        when(environment.getProperty((String) any())).thenReturn("Property");

        FeedData feedData = new FeedData();
        feedData.setContributionId("42");
        feedData.setContributionType("sendEmailLike");
        feedData.setDescription("The characteristics of someone or something");
        feedData.setEmail("jane.doe@example.org");
        feedData.setFeedComments(new ArrayList<>());
        feedData.setFeedId(1);
        feedData.setFeedLike(new ArrayList<>());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedData.setFeedTime(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        feedData.setFeedType("sendEmailLike");
        feedData.setLapseTimeDifference("sendEmailLike");
        feedData.setTenantId("42");
        feedData.setTitle("Dr");
        feedData.setUrl("https://example.org/example");
        feedData.setUserName("janedoe");
        feedData.setUserXref(1);

        FeedLike feedLike = new FeedLike();
        feedLike.setDislike(true);
        feedLike.setEmail("jane.doe@example.org");
        feedLike.setFeedData(feedData);
        feedLike.setFeedId(1);
        feedLike.setFeedLikeId(1);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedLike.setFeedTime(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        feedLike.setLikes(true);
        feedLike.setUserName("janedoe");

        ArrayList<FeedLike> feedLikeList = new ArrayList<>();
        feedLikeList.add(feedLike);

        FeedData feedData1 = new FeedData();
        feedData1.setContributionId("42");
        feedData1.setContributionType("Contribution Type");
        feedData1.setDescription("The characteristics of someone or something");
        feedData1.setEmail("jane.doe@example.org");
        feedData1.setFeedComments(new ArrayList<>());
        feedData1.setFeedId(1);
        feedData1.setFeedLike(feedLikeList);
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

        FeedLike feedLike1 = new FeedLike();
        feedLike1.setDislike(true);
        feedLike1.setEmail("jane.doe@example.org");
        feedLike1.setFeedData(feedData2);
        feedLike1.setFeedId(1);
        feedLike1.setFeedLikeId(1);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedLike1.setFeedTime(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        feedLike1.setLikes(true);
        feedLike1.setUserName("janedoe");
        doNothing().when(feedLikeRepository).delete((FeedLike) any());
        when(feedLikeRepository.save((FeedLike) any())).thenReturn(feedLike1);

        FeedData feedData3 = new FeedData();
        feedData3.setContributionId("42");
        feedData3.setContributionType("Contribution Type");
        feedData3.setDescription("The characteristics of someone or something");
        feedData3.setEmail("jane.doe@example.org");
        feedData3.setFeedComments(new ArrayList<>());
        feedData3.setFeedId(1);
        feedData3.setFeedLike(new ArrayList<>());
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedData3.setFeedTime(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        feedData3.setFeedType("Feed Type");
        feedData3.setLapseTimeDifference("Lapse Time Difference");
        feedData3.setTenantId("42");
        feedData3.setTitle("Dr");
        feedData3.setUrl("https://example.org/example");
        feedData3.setUserName("janedoe");
        feedData3.setUserXref(1);

        FeedLike feedLike2 = new FeedLike();
        feedLike2.setDislike(true);
        feedLike2.setEmail("jane.doe@example.org");
        feedLike2.setFeedData(feedData3);
        feedLike2.setFeedId(1);
        feedLike2.setFeedLikeId(1);
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedLike2.setFeedTime(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        feedLike2.setLikes(true);
        feedLike2.setUserName("janedoe");
        assertSame(feedLike2, feedLikeService.postFeedLike(1, feedLike2));
        verify(feedDataRepository).findById((Integer) any());
        verify(feedLikeRepository).delete((FeedLike) any());
    }

    /**
     * Method under test: {@link FeedLikeService#postFeedLike(int, FeedLike)}
     */
    @Test
    void testPostFeedLike7() {
        doNothing().when(emailService).sendEmail((SendEmailNotification) any());
        when(environment.getProperty((String) any())).thenReturn("Property");

        FeedData feedData = new FeedData();
        feedData.setContributionId("42");
        feedData.setContributionType("sendEmailLike");
        feedData.setDescription("The characteristics of someone or something");
        feedData.setEmail("jane.doe@example.org");
        feedData.setFeedComments(new ArrayList<>());
        feedData.setFeedId(1);
        feedData.setFeedLike(new ArrayList<>());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedData.setFeedTime(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        feedData.setFeedType("sendEmailLike");
        feedData.setLapseTimeDifference("sendEmailLike");
        feedData.setTenantId("42");
        feedData.setTitle("Dr");
        feedData.setUrl("https://example.org/example");
        feedData.setUserName("janedoe");
        feedData.setUserXref(1);

        FeedLike feedLike = new FeedLike();
        feedLike.setDislike(true);
        feedLike.setEmail("jane.doe@example.org");
        feedLike.setFeedData(feedData);
        feedLike.setFeedId(1);
        feedLike.setFeedLikeId(1);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedLike.setFeedTime(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        feedLike.setLikes(true);
        feedLike.setUserName("janedoe");

        FeedData feedData1 = new FeedData();
        feedData1.setContributionId("jane.doe@example.org");
        feedData1.setContributionType("Contribution Type");
        feedData1.setDescription("jane.doe@example.org");
        feedData1.setEmail("john.smith@example.org");
        feedData1.setFeedComments(new ArrayList<>());
        feedData1.setFeedId(2);
        feedData1.setFeedLike(new ArrayList<>());
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedData1.setFeedTime(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        feedData1.setFeedType("Feed Type");
        feedData1.setLapseTimeDifference("Lapse Time Difference");
        feedData1.setTenantId("jane.doe@example.org");
        feedData1.setTitle("Mr");
        feedData1.setUrl("jane.doe@example.org");
        feedData1.setUserName("jane.doe@example.org");
        feedData1.setUserXref(6);

        FeedLike feedLike1 = new FeedLike();
        feedLike1.setDislike(false);
        feedLike1.setEmail("john.smith@example.org");
        feedLike1.setFeedData(feedData1);
        feedLike1.setFeedId(2);
        feedLike1.setFeedLikeId(2);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedLike1.setFeedTime(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        feedLike1.setLikes(false);
        feedLike1.setUserName("jane.doe@example.org");

        ArrayList<FeedLike> feedLikeList = new ArrayList<>();
        feedLikeList.add(feedLike1);
        feedLikeList.add(feedLike);

        FeedData feedData2 = new FeedData();
        feedData2.setContributionId("42");
        feedData2.setContributionType("Contribution Type");
        feedData2.setDescription("The characteristics of someone or something");
        feedData2.setEmail("jane.doe@example.org");
        feedData2.setFeedComments(new ArrayList<>());
        feedData2.setFeedId(1);
        feedData2.setFeedLike(feedLikeList);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedData2.setFeedTime(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        feedData2.setFeedType("Feed Type");
        feedData2.setLapseTimeDifference("Lapse Time Difference");
        feedData2.setTenantId("42");
        feedData2.setTitle("Dr");
        feedData2.setUrl("https://example.org/example");
        feedData2.setUserName("janedoe");
        feedData2.setUserXref(1);
        Optional<FeedData> ofResult = Optional.of(feedData2);
        when(feedDataRepository.findById((Integer) any())).thenReturn(ofResult);

        FeedData feedData3 = new FeedData();
        feedData3.setContributionId("42");
        feedData3.setContributionType("Contribution Type");
        feedData3.setDescription("The characteristics of someone or something");
        feedData3.setEmail("jane.doe@example.org");
        feedData3.setFeedComments(new ArrayList<>());
        feedData3.setFeedId(1);
        feedData3.setFeedLike(new ArrayList<>());
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedData3.setFeedTime(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        feedData3.setFeedType("Feed Type");
        feedData3.setLapseTimeDifference("Lapse Time Difference");
        feedData3.setTenantId("42");
        feedData3.setTitle("Dr");
        feedData3.setUrl("https://example.org/example");
        feedData3.setUserName("janedoe");
        feedData3.setUserXref(1);

        FeedLike feedLike2 = new FeedLike();
        feedLike2.setDislike(true);
        feedLike2.setEmail("jane.doe@example.org");
        feedLike2.setFeedData(feedData3);
        feedLike2.setFeedId(1);
        feedLike2.setFeedLikeId(1);
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedLike2.setFeedTime(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        feedLike2.setLikes(true);
        feedLike2.setUserName("janedoe");
        doNothing().when(feedLikeRepository).delete((FeedLike) any());
        when(feedLikeRepository.save((FeedLike) any())).thenReturn(feedLike2);

        FeedData feedData4 = new FeedData();
        feedData4.setContributionId("42");
        feedData4.setContributionType("Contribution Type");
        feedData4.setDescription("The characteristics of someone or something");
        feedData4.setEmail("jane.doe@example.org");
        feedData4.setFeedComments(new ArrayList<>());
        feedData4.setFeedId(1);
        feedData4.setFeedLike(new ArrayList<>());
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedData4.setFeedTime(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        feedData4.setFeedType("Feed Type");
        feedData4.setLapseTimeDifference("Lapse Time Difference");
        feedData4.setTenantId("42");
        feedData4.setTitle("Dr");
        feedData4.setUrl("https://example.org/example");
        feedData4.setUserName("janedoe");
        feedData4.setUserXref(1);

        FeedLike feedLike3 = new FeedLike();
        feedLike3.setDislike(true);
        feedLike3.setEmail("jane.doe@example.org");
        feedLike3.setFeedData(feedData4);
        feedLike3.setFeedId(1);
        feedLike3.setFeedLikeId(1);
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedLike3.setFeedTime(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        feedLike3.setLikes(true);
        feedLike3.setUserName("janedoe");
        assertSame(feedLike3, feedLikeService.postFeedLike(1, feedLike3));
        verify(feedDataRepository).findById((Integer) any());
        verify(feedLikeRepository).delete((FeedLike) any());
    }

    /**
     * Method under test: {@link FeedLikeService#postFeedLike(int, FeedLike)}
     */
    @Test
    void testPostFeedLike8() {
        doNothing().when(emailService).sendEmail((SendEmailNotification) any());
        when(environment.getProperty((String) any())).thenReturn("Property");

        FeedData feedData = new FeedData();
        feedData.setContributionId("42");
        feedData.setContributionType("sendEmailLike");
        feedData.setDescription("The characteristics of someone or something");
        feedData.setEmail("jane.doe@example.org");
        feedData.setFeedComments(new ArrayList<>());
        feedData.setFeedId(1);
        feedData.setFeedLike(new ArrayList<>());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedData.setFeedTime(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        feedData.setFeedType("sendEmailLike");
        feedData.setLapseTimeDifference("sendEmailLike");
        feedData.setTenantId("42");
        feedData.setTitle("Dr");
        feedData.setUrl("https://example.org/example");
        feedData.setUserName("janedoe");
        feedData.setUserXref(1);
        FeedLike feedLike = mock(FeedLike.class);
        when(feedLike.getDislike()).thenThrow(new ExpressionException("An error occurred"));
        when(feedLike.getLikes()).thenThrow(new ExpressionException("An error occurred"));
        when(feedLike.getEmail()).thenReturn("jane.doe@example.org");
        doNothing().when(feedLike).setDislike((Boolean) any());
        doNothing().when(feedLike).setEmail((String) any());
        doNothing().when(feedLike).setFeedData((FeedData) any());
        doNothing().when(feedLike).setFeedId(anyInt());
        doNothing().when(feedLike).setFeedLikeId(anyInt());
        doNothing().when(feedLike).setFeedTime((Date) any());
        doNothing().when(feedLike).setLikes((Boolean) any());
        doNothing().when(feedLike).setUserName((String) any());
        feedLike.setDislike(true);
        feedLike.setEmail("jane.doe@example.org");
        feedLike.setFeedData(feedData);
        feedLike.setFeedId(1);
        feedLike.setFeedLikeId(1);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedLike.setFeedTime(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        feedLike.setLikes(true);
        feedLike.setUserName("janedoe");

        ArrayList<FeedLike> feedLikeList = new ArrayList<>();
        feedLikeList.add(feedLike);

        FeedData feedData1 = new FeedData();
        feedData1.setContributionId("42");
        feedData1.setContributionType("Contribution Type");
        feedData1.setDescription("The characteristics of someone or something");
        feedData1.setEmail("jane.doe@example.org");
        feedData1.setFeedComments(new ArrayList<>());
        feedData1.setFeedId(1);
        feedData1.setFeedLike(feedLikeList);
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

        FeedLike feedLike1 = new FeedLike();
        feedLike1.setDislike(true);
        feedLike1.setEmail("jane.doe@example.org");
        feedLike1.setFeedData(feedData2);
        feedLike1.setFeedId(1);
        feedLike1.setFeedLikeId(1);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedLike1.setFeedTime(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        feedLike1.setLikes(true);
        feedLike1.setUserName("janedoe");
        doNothing().when(feedLikeRepository).delete((FeedLike) any());
        when(feedLikeRepository.save((FeedLike) any())).thenReturn(feedLike1);

        FeedData feedData3 = new FeedData();
        feedData3.setContributionId("42");
        feedData3.setContributionType("Contribution Type");
        feedData3.setDescription("The characteristics of someone or something");
        feedData3.setEmail("jane.doe@example.org");
        feedData3.setFeedComments(new ArrayList<>());
        feedData3.setFeedId(1);
        feedData3.setFeedLike(new ArrayList<>());
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedData3.setFeedTime(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        feedData3.setFeedType("Feed Type");
        feedData3.setLapseTimeDifference("Lapse Time Difference");
        feedData3.setTenantId("42");
        feedData3.setTitle("Dr");
        feedData3.setUrl("https://example.org/example");
        feedData3.setUserName("janedoe");
        feedData3.setUserXref(1);

        FeedLike feedLike2 = new FeedLike();
        feedLike2.setDislike(true);
        feedLike2.setEmail("jane.doe@example.org");
        feedLike2.setFeedData(feedData3);
        feedLike2.setFeedId(1);
        feedLike2.setFeedLikeId(1);
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedLike2.setFeedTime(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        feedLike2.setLikes(true);
        feedLike2.setUserName("janedoe");
        assertThrows(ExpressionException.class, () -> feedLikeService.postFeedLike(1, feedLike2));
        verify(feedDataRepository).findById((Integer) any());
        verify(feedLike).getLikes();
        verify(feedLike).getEmail();
        verify(feedLike).setDislike((Boolean) any());
        verify(feedLike).setEmail((String) any());
        verify(feedLike).setFeedData((FeedData) any());
        verify(feedLike).setFeedId(anyInt());
        verify(feedLike).setFeedLikeId(anyInt());
        verify(feedLike).setFeedTime((Date) any());
        verify(feedLike).setLikes((Boolean) any());
        verify(feedLike).setUserName((String) any());
    }

    /**
     * Method under test: {@link FeedLikeService#postFeedLike(int, FeedLike)}
     */
    @Test
    void testPostFeedLike9() {
        doNothing().when(emailService).sendEmail((SendEmailNotification) any());
        when(environment.getProperty((String) any())).thenReturn("Property");

        FeedData feedData = new FeedData();
        feedData.setContributionId("42");
        feedData.setContributionType("sendEmailLike");
        feedData.setDescription("The characteristics of someone or something");
        feedData.setEmail("jane.doe@example.org");
        feedData.setFeedComments(new ArrayList<>());
        feedData.setFeedId(1);
        feedData.setFeedLike(new ArrayList<>());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedData.setFeedTime(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        feedData.setFeedType("sendEmailLike");
        feedData.setLapseTimeDifference("sendEmailLike");
        feedData.setTenantId("42");
        feedData.setTitle("Dr");
        feedData.setUrl("https://example.org/example");
        feedData.setUserName("janedoe");
        feedData.setUserXref(1);
        FeedLike feedLike = mock(FeedLike.class);
        when(feedLike.getDislike()).thenReturn(true);
        when(feedLike.getLikes()).thenReturn(false);
        when(feedLike.getEmail()).thenReturn("jane.doe@example.org");
        doNothing().when(feedLike).setDislike((Boolean) any());
        doNothing().when(feedLike).setEmail((String) any());
        doNothing().when(feedLike).setFeedData((FeedData) any());
        doNothing().when(feedLike).setFeedId(anyInt());
        doNothing().when(feedLike).setFeedLikeId(anyInt());
        doNothing().when(feedLike).setFeedTime((Date) any());
        doNothing().when(feedLike).setLikes((Boolean) any());
        doNothing().when(feedLike).setUserName((String) any());
        feedLike.setDislike(true);
        feedLike.setEmail("jane.doe@example.org");
        feedLike.setFeedData(feedData);
        feedLike.setFeedId(1);
        feedLike.setFeedLikeId(1);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedLike.setFeedTime(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        feedLike.setLikes(true);
        feedLike.setUserName("janedoe");

        ArrayList<FeedLike> feedLikeList = new ArrayList<>();
        feedLikeList.add(feedLike);

        FeedData feedData1 = new FeedData();
        feedData1.setContributionId("42");
        feedData1.setContributionType("Contribution Type");
        feedData1.setDescription("The characteristics of someone or something");
        feedData1.setEmail("jane.doe@example.org");
        feedData1.setFeedComments(new ArrayList<>());
        feedData1.setFeedId(1);
        feedData1.setFeedLike(feedLikeList);
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

        FeedLike feedLike1 = new FeedLike();
        feedLike1.setDislike(true);
        feedLike1.setEmail("jane.doe@example.org");
        feedLike1.setFeedData(feedData2);
        feedLike1.setFeedId(1);
        feedLike1.setFeedLikeId(1);
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedLike1.setFeedTime(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        feedLike1.setLikes(true);
        feedLike1.setUserName("janedoe");
        doNothing().when(feedLikeRepository).delete((FeedLike) any());
        when(feedLikeRepository.save((FeedLike) any())).thenReturn(feedLike1);

        FeedData feedData3 = new FeedData();
        feedData3.setContributionId("42");
        feedData3.setContributionType("Contribution Type");
        feedData3.setDescription("The characteristics of someone or something");
        feedData3.setEmail("jane.doe@example.org");
        feedData3.setFeedComments(new ArrayList<>());
        feedData3.setFeedId(1);
        feedData3.setFeedLike(new ArrayList<>());
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedData3.setFeedTime(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        feedData3.setFeedType("Feed Type");
        feedData3.setLapseTimeDifference("Lapse Time Difference");
        feedData3.setTenantId("42");
        feedData3.setTitle("Dr");
        feedData3.setUrl("https://example.org/example");
        feedData3.setUserName("janedoe");
        feedData3.setUserXref(1);

        FeedLike feedLike2 = new FeedLike();
        feedLike2.setDislike(true);
        feedLike2.setEmail("jane.doe@example.org");
        feedLike2.setFeedData(feedData3);
        feedLike2.setFeedId(1);
        feedLike2.setFeedLikeId(1);
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedLike2.setFeedTime(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        feedLike2.setLikes(true);
        feedLike2.setUserName("janedoe");
        assertSame(feedLike2, feedLikeService.postFeedLike(1, feedLike2));
        verify(feedDataRepository).findById((Integer) any());
        verify(feedLike, atLeast(1)).getDislike();
        verify(feedLike, atLeast(1)).getLikes();
        verify(feedLike).getEmail();
        verify(feedLike).setDislike((Boolean) any());
        verify(feedLike).setEmail((String) any());
        verify(feedLike).setFeedData((FeedData) any());
        verify(feedLike).setFeedId(anyInt());
        verify(feedLike).setFeedLikeId(anyInt());
        verify(feedLike).setFeedTime((Date) any());
        verify(feedLike).setLikes((Boolean) any());
        verify(feedLike).setUserName((String) any());
        verify(feedLikeRepository).delete((FeedLike) any());
    }

    /**
     * Method under test: {@link FeedLikeService#deleteFeedLike()}
     */
    @Test
    void testDeleteFeedLike() {
        doNothing().when(this.feedLikeRepository).deleteAll();
        this.feedLikeService.deleteFeedLike();
        verify(this.feedLikeRepository).deleteAll();
        assertTrue(this.feedLikeService.getFeedLike().isEmpty());
    }

    /**
     * Method under test: {@link FeedLikeService#deleteFeedLike()}
     */
    @Test
    void testDeleteFeedLike2() {
        doThrow(new ExpressionException("An error occurred")).when(this.feedLikeRepository).deleteAll();
        assertThrows(ExpressionException.class, () -> this.feedLikeService.deleteFeedLike());
        verify(this.feedLikeRepository).deleteAll();
    }

    /**
     * Method under test: {@link FeedLikeService#deleteFeedLike()}
     */
    @Test
    void testDeleteFeedLike3() {
        doNothing().when(feedLikeRepository).deleteAll();
        feedLikeService.deleteFeedLike();
        verify(feedLikeRepository).deleteAll();
        assertTrue(feedLikeService.getFeedLike().isEmpty());
    }

    /**
     * Method under test: {@link FeedLikeService#deleteFeedLike()}
     */
    @Test
    void testDeleteFeedLike4() {
        doThrow(new ExpressionException("An error occurred")).when(feedLikeRepository).deleteAll();
        assertThrows(ExpressionException.class, () -> feedLikeService.deleteFeedLike());
        verify(feedLikeRepository).deleteAll();
    }

    /**
     * Method under test: {@link FeedLikeService#updateFeedLike(int, FeedLike)}
     */
    @Test
    void testUpdateFeedLike() {
        FeedData feedData = new FeedData();
        feedData.setContributionId("123");
        feedData.setContributionType("Contribution Type");
        feedData.setDescription("The characteristics of someone or something");
        feedData.setEmail("jane.doe@example.org");
        feedData.setFeedComments(new ArrayList<>());
        feedData.setFeedId(123);
        feedData.setFeedLike(new ArrayList<>());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedData.setFeedTime(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        feedData.setTitle("Dr");
        feedData.setUrl("https://example.org/example");
        feedData.setUserName("janedoe");

        FeedLike feedLike = new FeedLike();
        feedLike.setDislike(true);
        feedLike.setEmail("jane.doe@example.org");
        feedLike.setFeedData(feedData);
        feedLike.setFeedId(123);
        feedLike.setFeedLikeId(123);
        feedLike.setLikes(true);
        feedLike.setUserName("janedoe");
        Optional<FeedLike> ofResult = Optional.of(feedLike);

        FeedData feedData1 = new FeedData();
        feedData1.setContributionId("123");
        feedData1.setContributionType("Contribution Type");
        feedData1.setDescription("The characteristics of someone or something");
        feedData1.setEmail("jane.doe@example.org");
        feedData1.setFeedComments(new ArrayList<>());
        feedData1.setFeedId(123);
        feedData1.setFeedLike(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedData1.setFeedTime(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        feedData1.setTitle("Dr");
        feedData1.setUrl("https://example.org/example");
        feedData1.setUserName("janedoe");

        FeedLike feedLike1 = new FeedLike();
        feedLike1.setDislike(true);
        feedLike1.setEmail("jane.doe@example.org");
        feedLike1.setFeedData(feedData1);
        feedLike1.setFeedId(123);
        feedLike1.setFeedLikeId(123);
        feedLike1.setLikes(true);
        feedLike1.setUserName("janedoe");
        when(this.feedLikeRepository.save((FeedLike) any())).thenReturn(feedLike1);
        when(this.feedLikeRepository.findById((Integer) any())).thenReturn(ofResult);

        FeedData feedData2 = new FeedData();
        feedData2.setContributionId("123");
        feedData2.setContributionType("Contribution Type");
        feedData2.setDescription("The characteristics of someone or something");
        feedData2.setEmail("jane.doe@example.org");
        feedData2.setFeedComments(new ArrayList<>());
        feedData2.setFeedId(123);
        feedData2.setFeedLike(new ArrayList<>());
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedData2.setFeedTime(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        feedData2.setTitle("Dr");
        feedData2.setUrl("https://example.org/example");
        feedData2.setUserName("janedoe");

        FeedLike feedLike2 = new FeedLike();
        feedLike2.setDislike(true);
        feedLike2.setEmail("jane.doe@example.org");
        feedLike2.setFeedData(feedData2);
        feedLike2.setFeedId(123);
        feedLike2.setFeedLikeId(123);
        feedLike2.setLikes(true);
        feedLike2.setUserName("janedoe");
        FeedLike actualUpdateFeedLikeResult = this.feedLikeService.updateFeedLike(1, feedLike2);
        assertSame(feedLike, actualUpdateFeedLikeResult);
        assertTrue(actualUpdateFeedLikeResult.getDislike());
        assertTrue(actualUpdateFeedLikeResult.getLikes());
        verify(this.feedLikeRepository).save((FeedLike) any());
        verify(this.feedLikeRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link FeedLikeService#updateFeedLike(int, FeedLike)}
     */
    @Test
    void testUpdateFeedLike2() {
        FeedData feedData = new FeedData();
        feedData.setContributionId("123");
        feedData.setContributionType("Contribution Type");
        feedData.setDescription("The characteristics of someone or something");
        feedData.setEmail("jane.doe@example.org");
        feedData.setFeedComments(new ArrayList<>());
        feedData.setFeedId(123);
        feedData.setFeedLike(new ArrayList<>());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedData.setFeedTime(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        feedData.setTitle("Dr");
        feedData.setUrl("https://example.org/example");
        feedData.setUserName("janedoe");

        FeedLike feedLike = new FeedLike();
        feedLike.setDislike(true);
        feedLike.setEmail("jane.doe@example.org");
        feedLike.setFeedData(feedData);
        feedLike.setFeedId(123);
        feedLike.setFeedLikeId(123);
        feedLike.setLikes(true);
        feedLike.setUserName("janedoe");
        Optional<FeedLike> ofResult = Optional.of(feedLike);
        when(this.feedLikeRepository.save((FeedLike) any())).thenThrow(new ExpressionException("An error occurred"));
        when(this.feedLikeRepository.findById((Integer) any())).thenReturn(ofResult);

        FeedData feedData1 = new FeedData();
        feedData1.setContributionId("123");
        feedData1.setContributionType("Contribution Type");
        feedData1.setDescription("The characteristics of someone or something");
        feedData1.setEmail("jane.doe@example.org");
        feedData1.setFeedComments(new ArrayList<>());
        feedData1.setFeedId(123);
        feedData1.setFeedLike(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedData1.setFeedTime(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        feedData1.setTitle("Dr");
        feedData1.setUrl("https://example.org/example");
        feedData1.setUserName("janedoe");

        FeedLike feedLike1 = new FeedLike();
        feedLike1.setDislike(true);
        feedLike1.setEmail("jane.doe@example.org");
        feedLike1.setFeedData(feedData1);
        feedLike1.setFeedId(123);
        feedLike1.setFeedLikeId(123);
        feedLike1.setLikes(true);
        feedLike1.setUserName("janedoe");
        assertThrows(ExpressionException.class, () -> this.feedLikeService.updateFeedLike(1, feedLike1));
        verify(this.feedLikeRepository).save((FeedLike) any());
        verify(this.feedLikeRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link FeedLikeService#updateFeedLike(int, FeedLike)}
     */
    @Test
    void testUpdateFeedLike3() {
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

        FeedLike feedLike = new FeedLike();
        feedLike.setDislike(true);
        feedLike.setEmail("jane.doe@example.org");
        feedLike.setFeedData(feedData);
        feedLike.setFeedId(1);
        feedLike.setFeedLikeId(1);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedLike.setFeedTime(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        feedLike.setLikes(true);
        feedLike.setUserName("janedoe");
        Optional<FeedLike> ofResult = Optional.of(feedLike);

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

        FeedLike feedLike1 = new FeedLike();
        feedLike1.setDislike(true);
        feedLike1.setEmail("jane.doe@example.org");
        feedLike1.setFeedData(feedData1);
        feedLike1.setFeedId(1);
        feedLike1.setFeedLikeId(1);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedLike1.setFeedTime(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        feedLike1.setLikes(true);
        feedLike1.setUserName("janedoe");
        when(feedLikeRepository.save((FeedLike) any())).thenReturn(feedLike1);
        when(feedLikeRepository.findById((Integer) any())).thenReturn(ofResult);

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

        FeedLike feedLike2 = new FeedLike();
        feedLike2.setDislike(true);
        feedLike2.setEmail("jane.doe@example.org");
        feedLike2.setFeedData(feedData2);
        feedLike2.setFeedId(1);
        feedLike2.setFeedLikeId(1);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedLike2.setFeedTime(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        feedLike2.setLikes(true);
        feedLike2.setUserName("janedoe");
        FeedLike actualUpdateFeedLikeResult = feedLikeService.updateFeedLike(1, feedLike2);
        assertSame(feedLike, actualUpdateFeedLikeResult);
        assertTrue(actualUpdateFeedLikeResult.getDislike());
        assertTrue(actualUpdateFeedLikeResult.getLikes());
        verify(feedLikeRepository).save((FeedLike) any());
        verify(feedLikeRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link FeedLikeService#updateFeedLike(int, FeedLike)}
     */
    @Test
    void testUpdateFeedLike4() {
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

        FeedLike feedLike = new FeedLike();
        feedLike.setDislike(true);
        feedLike.setEmail("jane.doe@example.org");
        feedLike.setFeedData(feedData);
        feedLike.setFeedId(1);
        feedLike.setFeedLikeId(1);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedLike.setFeedTime(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        feedLike.setLikes(true);
        feedLike.setUserName("janedoe");
        Optional<FeedLike> ofResult = Optional.of(feedLike);
        when(feedLikeRepository.save((FeedLike) any())).thenThrow(new ExpressionException("An error occurred"));
        when(feedLikeRepository.findById((Integer) any())).thenReturn(ofResult);

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

        FeedLike feedLike1 = new FeedLike();
        feedLike1.setDislike(true);
        feedLike1.setEmail("jane.doe@example.org");
        feedLike1.setFeedData(feedData1);
        feedLike1.setFeedId(1);
        feedLike1.setFeedLikeId(1);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedLike1.setFeedTime(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        feedLike1.setLikes(true);
        feedLike1.setUserName("janedoe");
        assertThrows(ExpressionException.class, () -> feedLikeService.updateFeedLike(1, feedLike1));
        verify(feedLikeRepository).save((FeedLike) any());
        verify(feedLikeRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link FeedLikeService#displayAllLikeUsername(int, int)}
     */
    @Test
    void testDisplayAllLikeUsername2() {
        when(this.feedLikeRepository.findUserNameForLikesByFeedId((org.springframework.data.domain.Pageable) any(),
                anyInt())).thenReturn(new PageImpl<>(new ArrayList<>()));
        when(this.environment.getProperty((String) any())).thenReturn("42");
        assertTrue(this.feedLikeService.displayAllLikeUsername(1, 123).isEmpty());
        verify(this.feedLikeRepository).findUserNameForLikesByFeedId((org.springframework.data.domain.Pageable) any(),
                anyInt());
        verify(this.environment).getProperty((String) any());
    }

    /**
     * Method under test: {@link FeedLikeService#displayAllLikeUsername(int, int)}
     */
    @Test
    void testDisplayAllLikeUsername3() {
        when(environment.getProperty((String) any())).thenReturn("42");
        when(feedLikeRepository.findUserNameForLikesByFeedId((Pageable) any(), anyInt()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        assertTrue(feedLikeService.displayAllLikeUsername(1, 1).isEmpty());
        verify(environment).getProperty((String) any());
        verify(feedLikeRepository).findUserNameForLikesByFeedId((Pageable) any(), anyInt());
    }

    /**
     * Method under test: {@link FeedLikeService#displayAllLikeUsername(int, int)}
     */
    @Test
    void testDisplayAllLikeUsername5() {
        when(this.feedLikeRepository.findUserNameForLikesByFeedId((org.springframework.data.domain.Pageable) any(),
                anyInt())).thenThrow(new ExpressionException("An error occurred"));
        when(this.environment.getProperty((String) any())).thenReturn("42");
        assertThrows(ExpressionException.class, () -> this.feedLikeService.displayAllLikeUsername(1, 123));
        verify(this.feedLikeRepository).findUserNameForLikesByFeedId((org.springframework.data.domain.Pageable) any(),
                anyInt());
        verify(this.environment).getProperty((String) any());
    }

    /**
     * Method under test: {@link FeedLikeService#displayAllLikeUsername(int, int)}
     */
    @Test
    void testDisplayAllLikeUsername7() {
        when(environment.getProperty((String) any())).thenReturn("42");
        when(feedLikeRepository.findUserNameForLikesByFeedId((Pageable) any(), anyInt()))
                .thenThrow(new ExpressionException("An error occurred"));
        assertThrows(ExpressionException.class, () -> feedLikeService.displayAllLikeUsername(1, 1));
        verify(environment).getProperty((String) any());
        verify(feedLikeRepository).findUserNameForLikesByFeedId((Pageable) any(), anyInt());
    }

    /**
     * Method under test: {@link FeedLikeService#displayAllDisLikeUsername(int, int)}
     */
    @Test
    void testDisplayAllDisLikeUsername2() {
        when(this.feedLikeRepository.findUserNameForDisLikesByFeedId((org.springframework.data.domain.Pageable) any(),
                anyInt())).thenReturn(new PageImpl<>(new ArrayList<>()));
        when(this.environment.getProperty((String) any())).thenReturn("42");
        assertTrue(this.feedLikeService.displayAllDisLikeUsername(1, 123).isEmpty());
        verify(this.feedLikeRepository).findUserNameForDisLikesByFeedId((org.springframework.data.domain.Pageable) any(),
                anyInt());
        verify(this.environment).getProperty((String) any());
    }

    /**
     * Method under test: {@link FeedLikeService#displayAllDisLikeUsername(int, int)}
     */
    @Test
    void testDisplayAllDisLikeUsername3() {
        when(environment.getProperty((String) any())).thenReturn("42");
        when(feedLikeRepository.findUserNameForDisLikesByFeedId((Pageable) any(), anyInt()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        assertTrue(feedLikeService.displayAllDisLikeUsername(1, 1).isEmpty());
        verify(environment).getProperty((String) any());
        verify(feedLikeRepository).findUserNameForDisLikesByFeedId((Pageable) any(), anyInt());
    }

    /**
     * Method under test: {@link FeedLikeService#displayAllDisLikeUsername(int, int)}
     */
    @Test
    void testDisplayAllDisLikeUsername5() {
        when(this.feedLikeRepository.findUserNameForDisLikesByFeedId((org.springframework.data.domain.Pageable) any(),
                anyInt())).thenThrow(new ExpressionException("An error occurred"));
        when(this.environment.getProperty((String) any())).thenReturn("42");
        assertThrows(ExpressionException.class, () -> this.feedLikeService.displayAllDisLikeUsername(1, 123));
        verify(this.feedLikeRepository).findUserNameForDisLikesByFeedId((org.springframework.data.domain.Pageable) any(),
                anyInt());
        verify(this.environment).getProperty((String) any());
    }

    /**
     * Method under test: {@link FeedLikeService#displayAllDisLikeUsername(int, int)}
     */
    @Test
    void testDisplayAllDisLikeUsername7() {
        when(environment.getProperty((String) any())).thenReturn("42");
        when(feedLikeRepository.findUserNameForDisLikesByFeedId((Pageable) any(), anyInt()))
                .thenThrow(new ExpressionException("An error occurred"));
        assertThrows(ExpressionException.class, () -> feedLikeService.displayAllDisLikeUsername(1, 1));
        verify(environment).getProperty((String) any());
        verify(feedLikeRepository).findUserNameForDisLikesByFeedId((Pageable) any(), anyInt());
    }
}

