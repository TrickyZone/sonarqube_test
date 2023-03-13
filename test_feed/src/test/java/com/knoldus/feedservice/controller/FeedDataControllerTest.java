package com.knoldus.feedservice.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.knoldus.feedservice.model.FeedData;
import com.knoldus.feedservice.service.FeedDataService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {FeedDataController.class})
@ExtendWith(SpringExtension.class)
class FeedDataControllerTest {
    @Autowired
    private FeedDataController feedDataController;

    @MockBean
    private FeedDataService feedDataService;



    /**
     * Method under test: {@link FeedDataController#postFeedData(FeedData)}
     */
    @Test
    void testPostFeedData() throws Exception {
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
        when(this.feedDataService.postFeedData((FeedData) any())).thenReturn(feedData);

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
        String content = (new ObjectMapper()).writeValueAsString(feedData1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/feed-data/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.feedDataController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"feedId\":123,\"contributionId\":\"123\",\"userName\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"contributionType"
                                        + "\":\"Contribution Type\",\"title\":\"Dr\",\"description\":\"The characteristics of someone or something\",\"feedTime"
                                        + "\":0,\"url\":\"https://example.org/example\",\"userXref\":0,\"feedLike\":[],\"feedComments\":[]}"));
    }

    /**
     * Method under test: {@link FeedDataController#postFeedData(FeedData)}
     */
    @Test
    void testPostFeedData2() throws Exception {
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
        when(feedDataService.postFeedData((FeedData) any())).thenReturn(feedData);

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
        String content = (new ObjectMapper()).writeValueAsString(feedData1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/feed-data/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(feedDataController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"feedId\":1,\"contributionId\":\"42\",\"userName\":\"janedoe\",\"tenantId\":\"42\",\"email\":\"jane.doe@example.org"
                                        + "\",\"contributionType\":\"Contribution Type\",\"title\":\"Dr\",\"description\":\"The characteristics of someone"
                                        + " or something\",\"feedTime\":0,\"lapseTimeDifference\":\"Lapse Time Difference\",\"url\":\"https://example.org"
                                        + "/example\",\"userXref\":1,\"feedType\":\"Feed Type\",\"feedLike\":[],\"feedComments\":[]}"));
    }

    /**
     * Method under test: {@link FeedDataController#deleteFeedData()}
     */
    @Test
    void testDeleteFeedData() throws Exception {
        doNothing().when(this.feedDataService).deleteFeedData();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/feed-data/delete");
        MockMvcBuilders.standaloneSetup(this.feedDataController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link FeedDataController#deleteFeedData()}
     */
    @Test
    void testDeleteFeedData2() throws Exception {
        doNothing().when(this.feedDataService).deleteFeedData();
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/feed-data/delete");
        deleteResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.feedDataController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link FeedDataController#deleteFeedData()}
     */
    @Test
    void testDeleteFeedData3() throws Exception {
        doNothing().when(feedDataService).deleteFeedData();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/feed-data/delete");
        MockMvcBuilders.standaloneSetup(feedDataController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link FeedDataController#deleteFeedData()}
     */
    @Test
    void testDeleteFeedData4() throws Exception {
        doNothing().when(feedDataService).deleteFeedData();
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/feed-data/delete");
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(feedDataController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link FeedDataController#updateFeedData(int, FeedData)}
     */
    @Test
    void testUpdateFeedData() throws Exception {
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
        Optional<FeedData> ofResult = Optional.of(feedData);
        when(this.feedDataService.updateFeedData(anyInt(), (FeedData) any())).thenReturn(ofResult);

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
        String content = (new ObjectMapper()).writeValueAsString(feedData1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/feed-data/update/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.feedDataController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"feedId\":123,\"contributionId\":\"123\",\"userName\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"contributionType"
                                        + "\":\"Contribution Type\",\"title\":\"Dr\",\"description\":\"The characteristics of someone or something\",\"feedTime"
                                        + "\":0,\"url\":\"https://example.org/example\",\"userXref\":0,\"feedLike\":[],\"feedComments\":[]}"));
    }

    /**
     * Method under test: {@link FeedDataController#updateFeedData(int, FeedData)}
     */
    @Test
    void testUpdateFeedData2() throws Exception {
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
        when(feedDataService.updateFeedData(anyInt(), (FeedData) any())).thenReturn(ofResult);

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
        String content = (new ObjectMapper()).writeValueAsString(feedData1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/feed-data/update/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(feedDataController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"feedId\":1,\"contributionId\":\"42\",\"userName\":\"janedoe\",\"tenantId\":\"42\",\"email\":\"jane.doe@example.org"
                                        + "\",\"contributionType\":\"Contribution Type\",\"title\":\"Dr\",\"description\":\"The characteristics of someone"
                                        + " or something\",\"feedTime\":0,\"lapseTimeDifference\":\"Lapse Time Difference\",\"url\":\"https://example.org"
                                        + "/example\",\"userXref\":1,\"feedType\":\"Feed Type\",\"feedLike\":[],\"feedComments\":[]}"));
    }

    /**
     * Method under test: {@link FeedDataController#getFeedData(int)}
     */
    @Test
    void testGetFeedData() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/feed-data/get/{pageNo}", "",
                "Uri Vars");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.feedDataController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link FeedDataController#getFeedDataById(int, int)}
     */
    @Test
    void testGetFeedDataById() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/feed-data/get/{id}", "", "Uri Vars");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.feedDataController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

