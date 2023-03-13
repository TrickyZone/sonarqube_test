package com.knoldus.feedservice.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.knoldus.feedservice.dao.FeedDataRepository;
import com.knoldus.feedservice.dao.FeedLikeRepository;
import com.knoldus.feedservice.model.FeedData;
import com.knoldus.feedservice.model.FeedLike;
import com.knoldus.feedservice.service.FeedLikeService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Date;

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

@ContextConfiguration(classes = {FeedLikeController.class})
@ExtendWith(SpringExtension.class)
class FeedLikeControllerTest {
    @MockBean
    private FeedDataRepository feedDataRepository;

    @Autowired
    private FeedLikeController feedLikeController;

    @MockBean
    private FeedLikeRepository feedLikeRepository;

    @MockBean
    private FeedLikeService feedLikeService;

    /**
     * Method under test: {@link FeedLikeController#getFeedLike()}
     */
    @Test
    void testGetFeedLike() throws Exception {
        when(this.feedLikeService.getFeedLike()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/feed-like/get");
        MockMvcBuilders.standaloneSetup(this.feedLikeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link FeedLikeController#getFeedLike()}
     */
    @Test
    void testGetFeedLike2() throws Exception {
        when(this.feedLikeService.getFeedLike()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/feed-like/get");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.feedLikeController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link FeedLikeController#postFeedLike(int, FeedLike)}
     */
    @Test
    void testPostFeedLike() throws Exception {
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
        when(this.feedLikeService.postFeedLike(anyInt(), (FeedLike) any())).thenReturn(feedLike);

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
        String content = (new ObjectMapper()).writeValueAsString(feedLike1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/feed-like/post/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.feedLikeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"feedLikeId\":123,\"email\":\"jane.doe@example.org\",\"likes\":true,\"dislike\":true,\"feedId\":123,\"userName\""
                                        + ":\"janedoe\"}"));
    }

    /**
     * Method under test: {@link FeedLikeController#deleteFeedLike()}
     */
    @Test
    void testDeleteFeedLike() throws Exception {
        doNothing().when(this.feedLikeService).deleteFeedLike();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/feed-like/delete");
        MockMvcBuilders.standaloneSetup(this.feedLikeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link FeedLikeController#deleteFeedLike()}
     */
    @Test
    void testDeleteFeedLike2() throws Exception {
        doNothing().when(this.feedLikeService).deleteFeedLike();
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/feed-like/delete");
        deleteResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.feedLikeController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link FeedLikeController#updateFeedLike(int, FeedLike)}
     */
    @Test
    void testUpdateFeedLike() throws Exception {
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
        when(this.feedLikeService.updateFeedLike(anyInt(), (FeedLike) any())).thenReturn(feedLike);

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
        String content = (new ObjectMapper()).writeValueAsString(feedLike1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/feed-like/update/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.feedLikeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"feedLikeId\":123,\"email\":\"jane.doe@example.org\",\"likes\":true,\"dislike\":true,\"feedId\":123,\"userName\""
                                        + ":\"janedoe\"}"));
    }
}

