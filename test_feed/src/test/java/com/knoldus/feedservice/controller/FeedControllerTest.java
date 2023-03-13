package com.knoldus.feedservice.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.knoldus.feedservice.model.FeedComment;
import com.knoldus.feedservice.model.FeedData;
import com.knoldus.feedservice.model.FeedLike;
import com.knoldus.feedservice.model.FeedStats;
import com.knoldus.feedservice.service.FeedCommentService;
import com.knoldus.feedservice.service.FeedDataService;
import com.knoldus.feedservice.service.FeedLikeService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {FeedController.class})
@ExtendWith(SpringExtension.class)
@PropertySource("classpath:application-test.properties")
@EnableConfigurationProperties
class FeedControllerTest {
    @MockBean
    private FeedCommentService feedCommentService;

    @Autowired
    private FeedController feedController;

    @MockBean
    private FeedDataService feedDataService;

    @MockBean
    private FeedLikeService feedLikeService;

    @MockBean
    private RabbitTemplate rabbitTemplate;

    /**
     * Method under test: {@link FeedController#addComment(int, FeedComment)}
     */
    @Test
    void testAddComment() throws Exception {
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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/knoldus-backend/rest/feed-service/feeds/comment/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(feedController)
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
     * Method under test: {@link FeedController#addComment(int, FeedComment)}
     */
    @Test
    void testAddComment2() throws Exception {
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
        feedComment1.setComment("");
        feedComment1.setEmail("jane.doe@example.org");
        feedComment1.setFeedCommentId(1);
        feedComment1.setFeedData(feedData1);
        feedComment1.setFeedId(1);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        feedComment1.setFeedTime(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        feedComment1.setUserName("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(feedComment1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/knoldus-backend/rest/feed-service/feeds/comment/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(feedController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link FeedController#getAllLatestFeedStats(int)}
     */
    @Test
    void testGetAllLatestFeedStats() throws Exception {
        when(feedDataService.getAllLatestFeedDataStats(anyInt())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/knoldus-backend/rest/feed-service/feeds/statistic/{pageNo}", 1);
        MockMvcBuilders.standaloneSetup(feedController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link FeedController#getAllLatestFeedStats(int)}
     */
    @Test
    void testGetAllLatestFeedStats2() throws Exception {
        when(feedDataService.getAllLatestFeedDataStats(anyInt())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/knoldus-backend/rest/feed-service/feeds/statistic/{pageNo}", 1);
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(feedController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link FeedController#getAllUsernameForDisLike(int, int)}
     */
    @Test
    void testGetAllUsernameForDisLike() throws Exception {
        when(feedLikeService.displayAllDisLikeUsername(anyInt(), anyInt())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/knoldus-backend/rest/feed-service/feeds/dislike/usernames/{feedId}/{pageNo}", 1, 1);
        MockMvcBuilders.standaloneSetup(feedController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link FeedController#getAllUsernameForDisLike(int, int)}
     */
    @Test
    void testGetAllUsernameForDisLike2() throws Exception {
        when(feedLikeService.displayAllDisLikeUsername(anyInt(), anyInt())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/knoldus-backend/rest/feed-service/feeds/dislike/usernames/{feedId}/{pageNo}", 1, 1);
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(feedController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link FeedController#getAllUsernameForLike(int, int)}
     */
    @Test
    void testGetAllUsernameForLike() throws Exception {
        when(feedLikeService.displayAllLikeUsername(anyInt(), anyInt())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/knoldus-backend/rest/feed-service/feeds/like/usernames/{feedId}/{pageNo}", 1, 1);
        MockMvcBuilders.standaloneSetup(feedController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link FeedController#getAllUsernameForLike(int, int)}
     */
    @Test
    void testGetAllUsernameForLike2() throws Exception {
        when(feedLikeService.displayAllLikeUsername(anyInt(), anyInt())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/knoldus-backend/rest/feed-service/feeds/like/usernames/{feedId}/{pageNo}", 1, 1);
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(feedController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link FeedController#getComments(int, int)}
     */
    @Test
    void testGetComments() throws Exception {
        when(feedDataService.getFeedCommentsOnFeedId(anyInt(), anyInt())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/knoldus-backend/rest/feed-service/feeds/comments/{id}/{pageNo}", 1, 1);
        MockMvcBuilders.standaloneSetup(feedController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link FeedController#getComments(int, int)}
     */
    @Test
    void testGetComments2() throws Exception {
        when(feedDataService.getFeedCommentsOnFeedId(anyInt(), anyInt())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/knoldus-backend/rest/feed-service/feeds/comments/{id}/{pageNo}", 1, 1);
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(feedController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link FeedController#getFeedStats(int)}
     */
    @Test
    void testGetFeedStats() throws Exception {
        when(feedDataService.getFeedDataStatsByFeedId(anyInt())).thenReturn(new FeedStats(1, 10, 10, 10));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/knoldus-backend/rest/feed-service/feeds/stats/{id}", 1);
        MockMvcBuilders.standaloneSetup(feedController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"feedId\":1,\"totalNumberOfLikes\":10,\"totalNumberOfDislikes\":10,\"totalNumberOfComments\":10}"));
    }

    /**
     * Method under test: {@link FeedController#getLatestFeedData(int, String)}
     */
    @Test
    void testGetLatestFeedData() throws Exception {
        when(feedDataService.getFeedDataWithTenantId((String) any(), anyInt())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/knoldus-backend/rest/feed-service/feeds/latest/data/{pageNo}/{tenantId}", 1, "42");
        MockMvcBuilders.standaloneSetup(feedController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link FeedController#getLatestFeedData(int, String)}
     */
    @Test
    void testGetLatestFeedData2() throws Exception {
        when(feedDataService.getFeedDataWithTenantId((String) any(), anyInt())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/knoldus-backend/rest/feed-service/feeds/latest/data/{pageNo}/{tenantId}", 1, "42");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(feedController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link FeedController#getUserActivityByEmail1(int, String)}
     */
    @Test
    void testGetUserActivityByEmail1() throws Exception {
        when(feedDataService.getAllDataByProfileEmail(anyInt(), (String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/knoldus-backend/rest/feed-service/feeds/userProfile/myActivity/{pageNo}/{email}", 1,
                "jane.doe@example.org");
        MockMvcBuilders.standaloneSetup(feedController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link FeedController#getUserActivityByEmail1(int, String)}
     */
    @Test
    void testGetUserActivityByEmail12() throws Exception {
        when(feedDataService.getAllDataByProfileEmail(anyInt(), (String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get(
                "/knoldus-backend/rest/feed-service/feeds/userProfile/myActivity/{pageNo}/{email}", 1,
                "jane.doe@example.org");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(feedController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link FeedController#hitLike(int, FeedLike)}
     */
    @Test
    void testHitLike() throws Exception {
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
        when(feedLikeService.postFeedLike(anyInt(), (FeedLike) any())).thenReturn(feedLike);

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
        String content = (new ObjectMapper()).writeValueAsString(feedLike1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/knoldus-backend/rest/feed-service/feeds/like/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(feedController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"feedLikeId\":1,\"email\":\"jane.doe@example.org\",\"likes\":true,\"dislike\":true,\"feedId\":1,\"userName\":"
                                        + "\"janedoe\",\"feedTime\":0}"));
    }
}

