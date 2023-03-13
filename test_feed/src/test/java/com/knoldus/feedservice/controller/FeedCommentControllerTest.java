package com.knoldus.feedservice.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.knoldus.feedservice.dao.FeedDataRepository;
import com.knoldus.feedservice.model.FeedComment;
import com.knoldus.feedservice.model.FeedData;
import com.knoldus.feedservice.service.FeedCommentService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {FeedCommentController.class})
@ExtendWith(SpringExtension.class)
class FeedCommentControllerTest {
    @Autowired
    private FeedCommentController feedCommentController;

    @MockBean
    private FeedCommentService feedCommentService;

    @MockBean
    private FeedDataRepository feedDataRepository;

    /**
     * Method under test: {@link FeedCommentController#getFeedCommentById(int)}
     */
    @Test
    void testGetFeedCommentById() throws Exception {
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

        FeedComment feedComment = new FeedComment();
        feedComment.setComment("Comment");
        feedComment.setEmail("jane.doe@example.org");
        feedComment.setFeedCommentId(123);
        feedComment.setFeedData(feedData);
        feedComment.setFeedId(123);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedComment.setFeedTime(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        feedComment.setUserName("janedoe");
        Optional<FeedComment> ofResult = Optional.of(feedComment);
        when(this.feedCommentService.getFeedCommentById(anyInt())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/feed-comment/get/{id}", 1);
        MockMvcBuilders.standaloneSetup(this.feedCommentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"feedCommentId\":123,\"email\":\"jane.doe@example.org\",\"comment\":\"Comment\",\"feedTime\":0,\"userName\":\"janedoe"
                                        + "\",\"feedId\":123}"));
    }

    /**
     * Method under test: {@link FeedCommentController#getFeedCommentById(int)}
     */
    @Test
    void testGetFeedCommentById2() throws Exception {
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

        FeedComment feedComment = new FeedComment();
        feedComment.setComment("Comment");
        feedComment.setEmail("jane.doe@example.org");
        feedComment.setFeedCommentId(123);
        feedComment.setFeedData(feedData);
        feedComment.setFeedId(123);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedComment.setFeedTime(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        feedComment.setUserName("janedoe");
        Optional<FeedComment> ofResult = Optional.of(feedComment);
        when(this.feedCommentService.getFeedCommentById(anyInt())).thenReturn(ofResult);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/feed-comment/get/{id}", 1);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.feedCommentController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"feedCommentId\":123,\"email\":\"jane.doe@example.org\",\"comment\":\"Comment\",\"feedTime\":0,\"userName\":\"janedoe"
                                        + "\",\"feedId\":123}"));
    }

    /**
     * Method under test: {@link FeedCommentController#getFeedCommentById(int)}
     */
    @Test
    void testGetFeedCommentById3() throws Exception {
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
        when(feedCommentService.getFeedCommentById(anyInt())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/feed-comment/get/{id}", 1);
        MockMvcBuilders.standaloneSetup(feedCommentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"feedCommentId\":1,\"email\":\"jane.doe@example.org\",\"comment\":\"Comment\",\"feedTime\":0,\"userName\":\"janedoe"
                                        + "\",\"feedId\":1}"));
    }

    /**
     * Method under test: {@link FeedCommentController#getFeedComments()}
     */
    @Test
    void testGetFeedComments3() throws Exception {
        when(feedCommentService.getFeedComment()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/feed-comment/get");
        MockMvcBuilders.standaloneSetup(feedCommentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link FeedCommentController#getFeedComments()}
     */
    @Test
    void testGetFeedComments4() throws Exception {
        when(feedCommentService.getFeedComment()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/feed-comment/get");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(feedCommentController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link FeedCommentController#postFeedComment(int, FeedComment)}
     */
    @Test
    void testPostFeedComment() throws Exception {
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

        FeedComment feedComment = new FeedComment();
        feedComment.setComment("Comment");
        feedComment.setEmail("jane.doe@example.org");
        feedComment.setFeedCommentId(123);
        feedComment.setFeedData(feedData);
        feedComment.setFeedId(123);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedComment.setFeedTime(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        feedComment.setUserName("janedoe");
        when(this.feedCommentService.postFeedComment(anyInt(), (FeedComment) any())).thenReturn(feedComment);

        FeedData feedData1 = new FeedData();
        feedData1.setContributionId("123");
        feedData1.setContributionType("Contribution Type");
        feedData1.setDescription("The characteristics of someone or something");
        feedData1.setEmail("jane.doe@example.org");
        feedData1.setFeedComments(new ArrayList<>());
        feedData1.setFeedId(123);
        feedData1.setFeedLike(new ArrayList<>());
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedData1.setFeedTime(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        feedData1.setTitle("Dr");
        feedData1.setUrl("https://example.org/example");
        feedData1.setUserName("janedoe");

        FeedComment feedComment1 = new FeedComment();
        feedComment1.setComment("Comment");
        feedComment1.setEmail("jane.doe@example.org");
        feedComment1.setFeedCommentId(123);
        feedComment1.setFeedData(feedData1);
        feedComment1.setFeedId(123);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedComment1.setFeedTime(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        feedComment1.setUserName("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(feedComment1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/feed-comment/post/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.feedCommentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"feedCommentId\":123,\"email\":\"jane.doe@example.org\",\"comment\":\"Comment\",\"feedTime\":0,\"userName\":\"janedoe"
                                        + "\",\"feedId\":123}"));
    }

    /**
     * Method under test: {@link FeedCommentController#postFeedComment(int, FeedComment)}
     */
    @Test
    void testPostFeedComment2() throws Exception {
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
        when(feedCommentService.postFeedComment(anyInt(), (FeedComment) any())).thenReturn(feedComment);

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
        String content = (new ObjectMapper()).writeValueAsString(feedComment1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/feed-comment/post/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(feedCommentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"feedCommentId\":1,\"email\":\"jane.doe@example.org\",\"comment\":\"Comment\",\"feedTime\":0,\"userName\":\"janedoe"
                                        + "\",\"feedId\":1}"));
    }

    /**
     * Method under test: {@link FeedCommentController#deleteFeedComment()}
     */
    @Test
    void testDeleteFeedComment() throws Exception {
        doNothing().when(this.feedCommentService).deleteFeedComment();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/feed-comment/delete");
        MockMvcBuilders.standaloneSetup(this.feedCommentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link FeedCommentController#deleteFeedComment()}
     */
    @Test
    void testDeleteFeedComment2() throws Exception {
        doNothing().when(this.feedCommentService).deleteFeedComment();
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/feed-comment/delete");
        deleteResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.feedCommentController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link FeedCommentController#deleteFeedComment()}
     */
    @Test
    void testDeleteFeedComment3() throws Exception {
        doNothing().when(feedCommentService).deleteFeedComment();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/feed-comment/delete");
        MockMvcBuilders.standaloneSetup(feedCommentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link FeedCommentController#deleteFeedComment()}
     */
    @Test
    void testDeleteFeedComment4() throws Exception {
        doNothing().when(feedCommentService).deleteFeedComment();
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/feed-comment/delete");
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(feedCommentController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link FeedCommentController#updateFeedComment(int, FeedComment)}
     */
    @Test
    void testUpdateFeedComment() throws Exception {
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

        FeedComment feedComment = new FeedComment();
        feedComment.setComment("Comment");
        feedComment.setEmail("jane.doe@example.org");
        feedComment.setFeedCommentId(123);
        feedComment.setFeedData(feedData);
        feedComment.setFeedId(123);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedComment.setFeedTime(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        feedComment.setUserName("janedoe");
        Optional<FeedComment> ofResult = Optional.of(feedComment);
        when(this.feedCommentService.updateFeedComment(anyInt(), (FeedComment) any())).thenReturn(ofResult);

        FeedData feedData1 = new FeedData();
        feedData1.setContributionId("123");
        feedData1.setContributionType("Contribution Type");
        feedData1.setDescription("The characteristics of someone or something");
        feedData1.setEmail("jane.doe@example.org");
        feedData1.setFeedComments(new ArrayList<>());
        feedData1.setFeedId(123);
        feedData1.setFeedLike(new ArrayList<>());
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedData1.setFeedTime(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        feedData1.setTitle("Dr");
        feedData1.setUrl("https://example.org/example");
        feedData1.setUserName("janedoe");

        FeedComment feedComment1 = new FeedComment();
        feedComment1.setComment("Comment");
        feedComment1.setEmail("jane.doe@example.org");
        feedComment1.setFeedCommentId(123);
        feedComment1.setFeedData(feedData1);
        feedComment1.setFeedId(123);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedComment1.setFeedTime(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        feedComment1.setUserName("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(feedComment1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/feed-comment/update/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.feedCommentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"feedCommentId\":123,\"email\":\"jane.doe@example.org\",\"comment\":\"Comment\",\"feedTime\":0,\"userName\":\"janedoe"
                                        + "\",\"feedId\":123}"));
    }

    /**
     * Method under test: {@link FeedCommentController#updateFeedComment(int, FeedComment)}
     */
    @Test
    void testUpdateFeedComment2() throws Exception {
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
        when(feedCommentService.updateFeedComment(anyInt(), (FeedComment) any())).thenReturn(ofResult);

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
        String content = (new ObjectMapper()).writeValueAsString(feedComment1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/feed-comment/update/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(feedCommentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"feedCommentId\":1,\"email\":\"jane.doe@example.org\",\"comment\":\"Comment\",\"feedTime\":0,\"userName\":\"janedoe"
                                        + "\",\"feedId\":1}"));
    }

    /**
     * Method under test: {@link FeedCommentController#getFeedComments()}
     */
    @Test
    void testGetFeedComments() throws Exception {
        when(this.feedCommentService.getFeedComment()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/feed-comment/get");
        MockMvcBuilders.standaloneSetup(this.feedCommentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link FeedCommentController#getFeedComments()}
     */
    @Test
    void testGetFeedComments2() throws Exception {
        when(this.feedCommentService.getFeedComment()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/feed-comment/get");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.feedCommentController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

