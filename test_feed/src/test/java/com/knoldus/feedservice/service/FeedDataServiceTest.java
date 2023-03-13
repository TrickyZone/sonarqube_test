package com.knoldus.feedservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.knoldus.feedservice.dao.FeedCommentRepository;
import com.knoldus.feedservice.dao.FeedDataRepository;
import com.knoldus.feedservice.dao.FeedLikeRepository;
import com.knoldus.feedservice.exception.RecordNotFoundException;
import com.knoldus.feedservice.model.FeedComment;
import com.knoldus.feedservice.model.FeedData;
import com.knoldus.feedservice.model.FeedStats;
import com.knoldus.feedservice.model.UserActivity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {FeedDataService.class})
@ExtendWith(SpringExtension.class)
class FeedDataServiceTest {
    @MockBean
    private Environment environment;

    @MockBean
    private FeedCommentRepository feedCommentRepository;

    @MockBean
    private FeedDataRepository feedDataRepository;

    @Autowired
    private FeedDataService feedDataService;

    @MockBean
    private FeedLikeRepository feedLikeRepository;





    /**
     * Method under test: {@link FeedDataService#getFeedData(int)}
     */
    @Test
    void testGetFeedData3() {
        when(environment.getProperty((String) any())).thenReturn("42");
        when(feedDataRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        assertTrue(feedDataService.getFeedData(1).isEmpty());
        verify(environment).getProperty((String) any());
        verify(feedDataRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link FeedDataService#getFeedData(int)}
     */
    @Test
    void testGetFeedData4() {
        when(environment.getProperty((String) any())).thenReturn("42");

        FeedData feedData = new FeedData();
        feedData.setContributionId("42");
        feedData.setContributionType("pageSize");
        feedData.setDescription("The characteristics of someone or something");
        feedData.setEmail("jane.doe@example.org");
        feedData.setFeedComments(new ArrayList<>());
        feedData.setFeedId(1);
        feedData.setFeedLike(new ArrayList<>());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedData.setFeedTime(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        feedData.setFeedType("pageSize");
        feedData.setLapseTimeDifference("pageSize");
        feedData.setTenantId("42");
        feedData.setTitle("Dr");
        feedData.setUrl("https://example.org/example");
        feedData.setUserName("janedoe");
        feedData.setUserXref(1);

        ArrayList<FeedData> feedDataList = new ArrayList<>();
        feedDataList.add(feedData);
        PageImpl<FeedData> pageImpl = new PageImpl<>(feedDataList);
        when(feedDataRepository.findAll((Pageable) any())).thenReturn(pageImpl);
        assertEquals(1, feedDataService.getFeedData(1).size());
        verify(environment).getProperty((String) any());
        verify(feedDataRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link FeedDataService#getFeedData(int)}
     */
    @Test
    void testGetFeedData5() {
        when(environment.getProperty((String) any())).thenReturn("42");

        FeedData feedData = new FeedData();
        feedData.setContributionId("42");
        feedData.setContributionType("pageSize");
        feedData.setDescription("The characteristics of someone or something");
        feedData.setEmail("jane.doe@example.org");
        feedData.setFeedComments(new ArrayList<>());
        feedData.setFeedId(1);
        feedData.setFeedLike(new ArrayList<>());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedData.setFeedTime(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        feedData.setFeedType("pageSize");
        feedData.setLapseTimeDifference("pageSize");
        feedData.setTenantId("42");
        feedData.setTitle("Dr");
        feedData.setUrl("https://example.org/example");
        feedData.setUserName("janedoe");
        feedData.setUserXref(1);

        FeedData feedData1 = new FeedData();
        feedData1.setContributionId("pageSize");
        feedData1.setContributionType("feedTime");
        feedData1.setDescription("pageSize");
        feedData1.setEmail("john.smith@example.org");
        feedData1.setFeedComments(new ArrayList<>());
        feedData1.setFeedId(2);
        feedData1.setFeedLike(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedData1.setFeedTime(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        feedData1.setFeedType("feedTime");
        feedData1.setLapseTimeDifference("feedTime");
        feedData1.setTenantId("pageSize");
        feedData1.setTitle("Mr");
        feedData1.setUrl("pageSize");
        feedData1.setUserName("pageSize");
        feedData1.setUserXref(19408);

        ArrayList<FeedData> feedDataList = new ArrayList<>();
        feedDataList.add(feedData1);
        feedDataList.add(feedData);
        PageImpl<FeedData> pageImpl = new PageImpl<>(feedDataList);
        when(feedDataRepository.findAll((Pageable) any())).thenReturn(pageImpl);
        assertEquals(2, feedDataService.getFeedData(1).size());
        verify(environment).getProperty((String) any());
        verify(feedDataRepository).findAll((Pageable) any());
    }



    /**
     * Method under test: {@link FeedDataService#getFeedData(int)}
     */
    @Test
    void testGetFeedData8() {
        when(environment.getProperty((String) any())).thenReturn("42");
        when(feedDataRepository.findAll((Pageable) any())).thenThrow(new RecordNotFoundException("pageSize"));
        assertThrows(RecordNotFoundException.class, () -> feedDataService.getFeedData(1));
        verify(environment).getProperty((String) any());
        verify(feedDataRepository).findAll((Pageable) any());
    }




    /**
     * Method under test: {@link FeedDataService#getFeedDataWithTenantId(String, int)}
     */
    @Test
    void testGetFeedDataWithTenantId3() {
        when(environment.getProperty((String) any())).thenReturn("42");
        when(feedDataRepository.findAllByTenantIdAndFeedTimeBetween((String) any(), (Date) any(), (Date) any(),
                (Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        assertTrue(feedDataService.getFeedDataWithTenantId("42", 1).isEmpty());
        verify(environment).getProperty((String) any());
        verify(feedDataRepository).findAllByTenantIdAndFeedTimeBetween((String) any(), (Date) any(), (Date) any(),
                (Pageable) any());
    }

    /**
     * Method under test: {@link FeedDataService#getFeedDataWithTenantId(String, int)}
     */
    @Test
    void testGetFeedDataWithTenantId4() {
        when(environment.getProperty((String) any())).thenReturn("42");

        FeedData feedData = new FeedData();
        feedData.setContributionId("42");
        feedData.setContributionType("pageSize");
        feedData.setDescription("The characteristics of someone or something");
        feedData.setEmail("jane.doe@example.org");
        feedData.setFeedComments(new ArrayList<>());
        feedData.setFeedId(1);
        feedData.setFeedLike(new ArrayList<>());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedData.setFeedTime(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        feedData.setFeedType("pageSize");
        feedData.setLapseTimeDifference("pageSize");
        feedData.setTenantId("42");
        feedData.setTitle("Dr");
        feedData.setUrl("https://example.org/example");
        feedData.setUserName("janedoe");
        feedData.setUserXref(1);

        ArrayList<FeedData> feedDataList = new ArrayList<>();
        feedDataList.add(feedData);
        PageImpl<FeedData> pageImpl = new PageImpl<>(feedDataList);
        when(feedDataRepository.findAllByTenantIdAndFeedTimeBetween((String) any(), (Date) any(), (Date) any(),
                (Pageable) any())).thenReturn(pageImpl);
        assertEquals(1, feedDataService.getFeedDataWithTenantId("42", 1).size());
        verify(environment).getProperty((String) any());
        verify(feedDataRepository).findAllByTenantIdAndFeedTimeBetween((String) any(), (Date) any(), (Date) any(),
                (Pageable) any());
    }

    /**
     * Method under test: {@link FeedDataService#getFeedDataWithTenantId(String, int)}
     */
    @Test
    void testGetFeedDataWithTenantId5() {
        when(environment.getProperty((String) any())).thenReturn("42");

        FeedData feedData = new FeedData();
        feedData.setContributionId("42");
        feedData.setContributionType("pageSize");
        feedData.setDescription("The characteristics of someone or something");
        feedData.setEmail("jane.doe@example.org");
        feedData.setFeedComments(new ArrayList<>());
        feedData.setFeedId(1);
        feedData.setFeedLike(new ArrayList<>());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedData.setFeedTime(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        feedData.setFeedType("pageSize");
        feedData.setLapseTimeDifference("pageSize");
        feedData.setTenantId("42");
        feedData.setTitle("Dr");
        feedData.setUrl("https://example.org/example");
        feedData.setUserName("janedoe");
        feedData.setUserXref(1);

        FeedData feedData1 = new FeedData();
        feedData1.setContributionId("pageSize");
        feedData1.setContributionType("feedTime");
        feedData1.setDescription("pageSize");
        feedData1.setEmail("john.smith@example.org");
        feedData1.setFeedComments(new ArrayList<>());
        feedData1.setFeedId(2);
        feedData1.setFeedLike(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedData1.setFeedTime(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        feedData1.setFeedType("feedTime");
        feedData1.setLapseTimeDifference("feedTime");
        feedData1.setTenantId("pageSize");
        feedData1.setTitle("Mr");
        feedData1.setUrl("pageSize");
        feedData1.setUserName("pageSize");
        feedData1.setUserXref(5);

        ArrayList<FeedData> feedDataList = new ArrayList<>();
        feedDataList.add(feedData1);
        feedDataList.add(feedData);
        PageImpl<FeedData> pageImpl = new PageImpl<>(feedDataList);
        when(feedDataRepository.findAllByTenantIdAndFeedTimeBetween((String) any(), (Date) any(), (Date) any(),
                (Pageable) any())).thenReturn(pageImpl);
        assertEquals(2, feedDataService.getFeedDataWithTenantId("42", 1).size());
        verify(environment).getProperty((String) any());
        verify(feedDataRepository).findAllByTenantIdAndFeedTimeBetween((String) any(), (Date) any(), (Date) any(),
                (Pageable) any());
    }



    /**
     * Method under test: {@link FeedDataService#getFeedDataWithTenantId(String, int)}
     */
    @Test
    void testGetFeedDataWithTenantId8() {
        when(environment.getProperty((String) any())).thenReturn("42");
        when(feedDataRepository.findAllByTenantIdAndFeedTimeBetween((String) any(), (Date) any(), (Date) any(),
                (Pageable) any())).thenThrow(new RecordNotFoundException("pageSize"));
        assertThrows(RecordNotFoundException.class, () -> feedDataService.getFeedDataWithTenantId("42", 1));
        verify(environment).getProperty((String) any());
        verify(feedDataRepository).findAllByTenantIdAndFeedTimeBetween((String) any(), (Date) any(), (Date) any(),
                (Pageable) any());
    }


    /**
     * Method under test: {@link FeedDataService#getFeedDataByFeedId(int)}
     */
    @Test
    void testGetFeedDataByFeedId2() {
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
        Optional<FeedData> actualFeedDataByFeedId = feedDataService.getFeedDataByFeedId(1);
        assertSame(ofResult, actualFeedDataByFeedId);
        assertTrue(actualFeedDataByFeedId.isPresent());
        verify(feedDataRepository).findById((Integer) any());
    }



    /**
     * Method under test: {@link FeedDataService#getFeedCommentsOnFeedId(int, int)}
     */
    @Test
    void testGetFeedCommentsOnFeedId3() {
        when(environment.getProperty((String) any())).thenThrow(new RecordNotFoundException("pageSize"));

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
        assertThrows(RecordNotFoundException.class, () -> feedDataService.getFeedCommentsOnFeedId(1, 1));
        verify(environment).getProperty((String) any());
        verify(feedDataRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link FeedDataService#getFeedCommentsOnFeedId(int, int)}
     */
    @Test
    void testGetFeedCommentsOnFeedId4() {
        when(environment.getProperty((String) any())).thenReturn("42");
        ArrayList<FeedComment> feedCommentList = new ArrayList<>();
        when(feedCommentRepository.findByFeedDataOrderByFeedCommentIdDesc((FeedData) any(), (Pageable) any()))
                .thenReturn(feedCommentList);

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
        List<FeedComment> actualFeedCommentsOnFeedId = feedDataService.getFeedCommentsOnFeedId(1, 1);
        assertSame(feedCommentList, actualFeedCommentsOnFeedId);
        assertTrue(actualFeedCommentsOnFeedId.isEmpty());
        verify(environment).getProperty((String) any());
        verify(feedCommentRepository).findByFeedDataOrderByFeedCommentIdDesc((FeedData) any(), (Pageable) any());
        verify(feedDataRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link FeedDataService#getFeedCommentsOnFeedId(int, int)}
     */
    @Test
    void testGetFeedCommentsOnFeedId5() {
        when(environment.getProperty((String) any())).thenReturn("42");
        when(feedCommentRepository.findByFeedDataOrderByFeedCommentIdDesc((FeedData) any(), (Pageable) any()))
                .thenReturn(new ArrayList<>());
        when(feedDataRepository.findById((Integer) any())).thenReturn(Optional.empty());
        assertThrows(RecordNotFoundException.class, () -> feedDataService.getFeedCommentsOnFeedId(1, 1));
        verify(feedDataRepository).findById((Integer) any());
    }




    /**
     * Method under test: {@link FeedDataService#getFeedCommentsOnFeedId(int, int)}
     */
    @Test
    void testGetFeedCommentsOnFeedId7() {
        when(environment.getProperty((String) any())).thenReturn("42");
        when(feedCommentRepository.findByFeedDataOrderByFeedCommentIdDesc((FeedData) any(), (Pageable) any()))
                .thenThrow(new RecordNotFoundException("pageSize"));

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
        assertThrows(RecordNotFoundException.class, () -> feedDataService.getFeedCommentsOnFeedId(1, 1));
        verify(environment).getProperty((String) any());
        verify(feedCommentRepository).findByFeedDataOrderByFeedCommentIdDesc((FeedData) any(), (Pageable) any());
        verify(feedDataRepository).findById((Integer) any());
    }


    /**
     * Method under test: {@link FeedDataService#getFeedDataStatsByFeedId(int)}
     */
    @Test
    void testGetFeedDataStatsByFeedId2() {
        when(feedCommentRepository.totalNoOfCommentsByFeedId(anyInt())).thenReturn(1);
        when(feedLikeRepository.totalNoOfDislikesByFeedId(anyInt())).thenReturn(1);
        when(feedLikeRepository.totalNoOfLikesByFeedId(anyInt())).thenReturn(1);
        FeedStats actualFeedDataStatsByFeedId = feedDataService.getFeedDataStatsByFeedId(1);
        assertEquals(1, actualFeedDataStatsByFeedId.getFeedId());
        assertEquals(1, actualFeedDataStatsByFeedId.getTotalNumberOfLikes());
        assertEquals(1, actualFeedDataStatsByFeedId.getTotalNumberOfDislikes());
        assertEquals(1, actualFeedDataStatsByFeedId.getTotalNumberOfComments());
        verify(feedCommentRepository).totalNoOfCommentsByFeedId(anyInt());
        verify(feedLikeRepository).totalNoOfDislikesByFeedId(anyInt());
        verify(feedLikeRepository).totalNoOfLikesByFeedId(anyInt());
    }

    /**
     * Method under test: {@link FeedDataService#getFeedDataStatsByFeedId(int)}
     */
    @Test
    void testGetFeedDataStatsByFeedId3() {
        when(feedCommentRepository.totalNoOfCommentsByFeedId(anyInt())).thenReturn(1);
        when(feedLikeRepository.totalNoOfDislikesByFeedId(anyInt())).thenThrow(new RecordNotFoundException("Exception"));
        when(feedLikeRepository.totalNoOfLikesByFeedId(anyInt())).thenThrow(new RecordNotFoundException("Exception"));
        assertThrows(RecordNotFoundException.class, () -> feedDataService.getFeedDataStatsByFeedId(1));
        verify(feedLikeRepository).totalNoOfLikesByFeedId(anyInt());
    }



    /**
     * Method under test: {@link FeedDataService#postFeedData(FeedData)}
     */
    @Test
    void testPostFeedData2() {
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
        assertSame(feedData, feedDataService.postFeedData(feedData1));
        verify(feedDataRepository).save((FeedData) any());
    }

    /**
     * Method under test: {@link FeedDataService#postFeedData(FeedData)}
     */
    @Test
    void testPostFeedData3() {
        when(feedDataRepository.save((FeedData) any())).thenThrow(new RecordNotFoundException("Exception"));

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
        assertThrows(RecordNotFoundException.class, () -> feedDataService.postFeedData(feedData));
        verify(feedDataRepository).save((FeedData) any());
    }


    /**
     * Method under test: {@link FeedDataService#deleteFeedData()}
     */
    @Test
    void testDeleteFeedData2() {
        doNothing().when(feedDataRepository).deleteAll();
        feedDataService.deleteFeedData();
        verify(feedDataRepository).deleteAll();
    }

    /**
     * Method under test: {@link FeedDataService#deleteFeedData()}
     */
    @Test
    void testDeleteFeedData3() {
        doThrow(new RecordNotFoundException("Exception")).when(feedDataRepository).deleteAll();
        assertThrows(RecordNotFoundException.class, () -> feedDataService.deleteFeedData());
        verify(feedDataRepository).deleteAll();
    }


    /**
     * Method under test: {@link FeedDataService#updateFeedData(int, FeedData)}
     */
    @Test
    void testUpdateFeedData2() {
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
        when(feedDataRepository.save((FeedData) any())).thenReturn(feedData1);
        when(feedDataRepository.findById((Integer) any())).thenReturn(ofResult);

        FeedData feedData2 = new FeedData();
        feedData2.setContributionId("42");
        feedData2.setContributionType("Contribution Type");
        feedData2.setDescription("The characteristics of someone or something");
        feedData2.setEmail("jane.doe@example.org");
        feedData2.setFeedComments(new ArrayList<>());
        feedData2.setFeedId(1);
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
        Optional<FeedData> actualUpdateFeedDataResult = feedDataService.updateFeedData(1, feedData2);
        assertSame(ofResult, actualUpdateFeedDataResult);
        assertTrue(actualUpdateFeedDataResult.isPresent());
        verify(feedDataRepository).save((FeedData) any());
        verify(feedDataRepository, atLeast(1)).findById((Integer) any());
    }

    /**
     * Method under test: {@link FeedDataService#updateFeedData(int, FeedData)}
     */
    @Test
    void testUpdateFeedData3() {
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
        when(feedDataRepository.save((FeedData) any())).thenThrow(new RecordNotFoundException("Exception"));
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
        assertThrows(RecordNotFoundException.class, () -> feedDataService.updateFeedData(1, feedData1));
        verify(feedDataRepository).save((FeedData) any());
        verify(feedDataRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link FeedDataService#updateFeedData(int, FeedData)}
     */
    @Test
    void testUpdateFeedData4() {
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
        Optional<FeedData> emptyResult = Optional.empty();
        when(feedDataRepository.findById((Integer) any())).thenReturn(emptyResult);

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
        Optional<FeedData> actualUpdateFeedDataResult = feedDataService.updateFeedData(1, feedData1);
        assertSame(emptyResult, actualUpdateFeedDataResult);
        assertFalse(actualUpdateFeedDataResult.isPresent());
        verify(feedDataRepository, atLeast(1)).findById((Integer) any());
    }


    /**
     * Method under test: {@link FeedDataService#getAllLatestFeedDataStats(int)}
     */
    @Test
    void testGetAllLatestFeedDataStats3() {
        when(environment.getProperty((String) any())).thenReturn("42");
        when(feedDataRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        assertTrue(feedDataService.getAllLatestFeedDataStats(1).isEmpty());
        verify(environment).getProperty((String) any());
        verify(feedDataRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link FeedDataService#getAllLatestFeedDataStats(int)}
     */
    @Test
    void testGetAllLatestFeedDataStats4() {
        when(environment.getProperty((String) any())).thenReturn("42");
        when(feedCommentRepository.totalNoOfCommentsByFeedId(anyInt())).thenReturn(1);

        FeedData feedData = new FeedData();
        feedData.setContributionId("42");
        feedData.setContributionType("pageSize");
        feedData.setDescription("The characteristics of someone or something");
        feedData.setEmail("jane.doe@example.org");
        feedData.setFeedComments(new ArrayList<>());
        feedData.setFeedId(1);
        feedData.setFeedLike(new ArrayList<>());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedData.setFeedTime(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        feedData.setFeedType("pageSize");
        feedData.setLapseTimeDifference("pageSize");
        feedData.setTenantId("42");
        feedData.setTitle("Dr");
        feedData.setUrl("https://example.org/example");
        feedData.setUserName("janedoe");
        feedData.setUserXref(1);

        ArrayList<FeedData> feedDataList = new ArrayList<>();
        feedDataList.add(feedData);
        PageImpl<FeedData> pageImpl = new PageImpl<>(feedDataList);
        when(feedDataRepository.findAll((Pageable) any())).thenReturn(pageImpl);
        when(feedLikeRepository.totalNoOfDislikesByFeedId(anyInt())).thenReturn(1);
        when(feedLikeRepository.totalNoOfLikesByFeedId(anyInt())).thenReturn(1);
        List<FeedStats> actualAllLatestFeedDataStats = feedDataService.getAllLatestFeedDataStats(1);
        assertEquals(1, actualAllLatestFeedDataStats.size());
        FeedStats getResult = actualAllLatestFeedDataStats.get(0);
        assertEquals(1, getResult.getFeedId());
        assertEquals(1, getResult.getTotalNumberOfLikes());
        assertEquals(1, getResult.getTotalNumberOfDislikes());
        assertEquals(1, getResult.getTotalNumberOfComments());
        verify(environment).getProperty((String) any());
        verify(feedCommentRepository).totalNoOfCommentsByFeedId(anyInt());
        verify(feedDataRepository).findAll((Pageable) any());
        verify(feedLikeRepository).totalNoOfDislikesByFeedId(anyInt());
        verify(feedLikeRepository).totalNoOfLikesByFeedId(anyInt());
    }

    /**
     * Method under test: {@link FeedDataService#getAllLatestFeedDataStats(int)}
     */
    @Test
    void testGetAllLatestFeedDataStats5() {
        when(environment.getProperty((String) any())).thenReturn("42");
        when(feedCommentRepository.totalNoOfCommentsByFeedId(anyInt())).thenReturn(1);

        FeedData feedData = new FeedData();
        feedData.setContributionId("42");
        feedData.setContributionType("pageSize");
        feedData.setDescription("The characteristics of someone or something");
        feedData.setEmail("jane.doe@example.org");
        feedData.setFeedComments(new ArrayList<>());
        feedData.setFeedId(1);
        feedData.setFeedLike(new ArrayList<>());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedData.setFeedTime(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        feedData.setFeedType("pageSize");
        feedData.setLapseTimeDifference("pageSize");
        feedData.setTenantId("42");
        feedData.setTitle("Dr");
        feedData.setUrl("https://example.org/example");
        feedData.setUserName("janedoe");
        feedData.setUserXref(1);

        ArrayList<FeedData> feedDataList = new ArrayList<>();
        feedDataList.add(feedData);
        PageImpl<FeedData> pageImpl = new PageImpl<>(feedDataList);
        when(feedDataRepository.findAll((Pageable) any())).thenReturn(pageImpl);
        when(feedLikeRepository.totalNoOfDislikesByFeedId(anyInt())).thenThrow(new RecordNotFoundException("pageSize"));
        when(feedLikeRepository.totalNoOfLikesByFeedId(anyInt())).thenThrow(new RecordNotFoundException("pageSize"));
        assertThrows(RecordNotFoundException.class, () -> feedDataService.getAllLatestFeedDataStats(1));
        verify(environment).getProperty((String) any());
        verify(feedDataRepository).findAll((Pageable) any());
        verify(feedLikeRepository).totalNoOfLikesByFeedId(anyInt());
    }







    /**
     * Method under test: {@link FeedDataService#getAllDataByProfileEmail(int, String)}
     */
    @Test
    void testGetAllDataByProfileEmail3() {
        when(environment.getProperty((String) any())).thenReturn("42");
        when(feedDataRepository.findbyUserEmailId((String) any())).thenReturn(new ArrayList<>());
        assertTrue(feedDataService.getAllDataByProfileEmail(1, "jane.doe@example.org").isEmpty());
        verify(environment).getProperty((String) any());
        verify(feedDataRepository).findbyUserEmailId((String) any());
    }

    /**
     * Method under test: {@link FeedDataService#getAllDataByProfileEmail(int, String)}
     */
    @Test
    void testGetAllDataByProfileEmail4() {
        when(environment.getProperty((String) any())).thenReturn("42");

        ArrayList<UserActivity> userActivityList = new ArrayList<>();
        userActivityList.add(new UserActivity());
        when(feedDataRepository.findbyUserEmailId((String) any())).thenReturn(userActivityList);
        assertEquals(1, feedDataService.getAllDataByProfileEmail(1, "jane.doe@example.org").size());
        verify(environment).getProperty((String) any());
        verify(feedDataRepository).findbyUserEmailId((String) any());
    }

    /**
     * Method under test: {@link FeedDataService#getAllDataByProfileEmail(int, String)}
     */
    @Test
    void testGetAllDataByProfileEmail5() {
        when(environment.getProperty((String) any())).thenReturn("42");

        ArrayList<UserActivity> userActivityList = new ArrayList<>();
        userActivityList.add(new UserActivity());
        userActivityList.add(new UserActivity());
        when(feedDataRepository.findbyUserEmailId((String) any())).thenReturn(userActivityList);
        assertEquals(2, feedDataService.getAllDataByProfileEmail(1, "jane.doe@example.org").size());
        verify(environment).getProperty((String) any());
        verify(feedDataRepository).findbyUserEmailId((String) any());
    }



    /**
     * Method under test: {@link FeedDataService#getAllDataByProfileEmail(int, String)}
     */
    @Test
    void testGetAllDataByProfileEmail7() {
        when(environment.getProperty((String) any())).thenReturn("42");
        when(feedDataRepository.findbyUserEmailId((String) any())).thenThrow(new RecordNotFoundException("pageSize"));
        assertThrows(RecordNotFoundException.class,
                () -> feedDataService.getAllDataByProfileEmail(1, "jane.doe@example.org"));
        verify(environment).getProperty((String) any());
        verify(feedDataRepository).findbyUserEmailId((String) any());
    }

    /**
     * Method under test: {@link FeedDataService#getAllDataByProfileEmail(int, String)}
     */
    @Test
    void testGetAllDataByProfileEmail8() {
        when(environment.getProperty((String) any())).thenReturn("42");

        ArrayList<UserActivity> userActivityList = new ArrayList<>();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        userActivityList.add(new UserActivity("pageSize", "janedoe", true, true, "pageSize", "pageSize",
                "The characteristics of someone or something", "pageSize", "Dr",
                Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant())));
        when(feedDataRepository.findbyUserEmailId((String) any())).thenReturn(userActivityList);
        assertEquals(1, feedDataService.getAllDataByProfileEmail(1, "jane.doe@example.org").size());
        verify(environment).getProperty((String) any());
        verify(feedDataRepository).findbyUserEmailId((String) any());
    }

    /**
     * Method under test: {@link FeedDataService#getAllDataByProfileEmail(int, String)}
     */

    @Test
    void testGetAllDataByProfileEmail10() {
        when(environment.getProperty((String) any())).thenReturn("42");
        UserActivity userActivity = mock(UserActivity.class);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        when(userActivity.getFeedtime()).thenReturn(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        doNothing().when(userActivity).setLapseTimeDifference((String) any());

        ArrayList<UserActivity> userActivityList = new ArrayList<>();
        userActivityList.add(userActivity);
        when(feedDataRepository.findbyUserEmailId((String) any())).thenReturn(userActivityList);
        assertEquals(1, feedDataService.getAllDataByProfileEmail(1, "jane.doe@example.org").size());
        verify(environment).getProperty((String) any());
        verify(feedDataRepository).findbyUserEmailId((String) any());
        verify(userActivity, atLeast(1)).getFeedtime();
        verify(userActivity).setLapseTimeDifference((String) any());
    }

    /**
     * Method under test: {@link FeedDataService#getAllDataByProfileEmail(int, String)}
     */
    @Test
    void testGetAllDataByProfileEmail11() {
        when(environment.getProperty((String) any())).thenReturn("42");
        UserActivity userActivity = mock(UserActivity.class);
        LocalDateTime atStartOfDayResult = LocalDate.of(19408, 1, 1).atStartOfDay();
        when(userActivity.getFeedtime()).thenReturn(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        doNothing().when(userActivity).setLapseTimeDifference((String) any());

        ArrayList<UserActivity> userActivityList = new ArrayList<>();
        userActivityList.add(userActivity);
        when(feedDataRepository.findbyUserEmailId((String) any())).thenReturn(userActivityList);
        assertEquals(1, feedDataService.getAllDataByProfileEmail(1, "jane.doe@example.org").size());
        verify(environment).getProperty((String) any());
        verify(feedDataRepository).findbyUserEmailId((String) any());
        verify(userActivity, atLeast(1)).getFeedtime();
        verify(userActivity).setLapseTimeDifference((String) any());
    }
}

