package com.knoldus.feedservice.controller;


import com.knoldus.feedservice.model.*;
import com.knoldus.feedservice.service.FeedCommentService;
import com.knoldus.feedservice.service.FeedDataService;
import com.knoldus.feedservice.service.FeedLikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * This controller is responsible to post and update the feeds.
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/knoldus-backend/rest/feed-service/feeds")
@Api("Feed API endpoints which are used for posting and updating the actions or reactions on the feeds.")
public class FeedController {

    @Autowired
    private RabbitTemplate template;
    @Autowired
    private FeedLikeService feedLikeService;
    @Autowired
    private FeedCommentService feedCommentService;
    @Autowired
    private FeedDataService feedDataService;

    public FeedController(RabbitTemplate templateMock) {
    }

    @ApiOperation(
            value = "Successfully added the positive reaction to the feed."
    )
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "posted like successfully."
            ),
            @ApiResponse(
                    code = 400,
                    message = "the request payload is not correct, or the feed is not specified."
            )
    })
    @PostMapping(path = "/like/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<FeedLike> hitLike(@PathVariable(value = "id", required = true) int feedId,
                                            @Valid @RequestBody FeedLike feedLike) {
        FeedLike likes = feedLikeService.postFeedLike(feedId, feedLike);
        if (likes != null) {
            return new ResponseEntity<>(likes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    @ApiOperation(
            value = "Successfully added the comment to the feed."
    )
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "added comment successfully."
            ),
            @ApiResponse(
                    code = 400,
                    message = "the request payload is not correct, or the feed is not specified."
            )
    })
    @PostMapping(path = "/comment/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<FeedComment> addComment(@PathVariable(value = "id") int feedId,
                                                  @Valid @RequestBody FeedComment feedComment) {
        FeedComment comments = feedCommentService.postFeedComment(feedId, feedComment);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Get all the latest feed data."
    )
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Retrieves the latest data of the feeds."
            ),
            @ApiResponse(
                    code = 400,
                    message = "the requested data is not found."
            )
    })
    @GetMapping(path = "/latest/data/{pageNo}/{tenantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FeedData>> getLatestFeedData(@PathVariable("pageNo") int pageNo, @PathVariable("tenantId") String tenantId) {
        List<FeedData> listOfLatestData = feedDataService.getFeedDataWithTenantId(tenantId,pageNo);
        return new ResponseEntity<>(listOfLatestData, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Get all the comments added to the feed."
    )
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Retrieves the comments posted on the feeds."
            ),
            @ApiResponse(
                    code = 400,
                    message = "the requested data is not found."
            )
    })
    @GetMapping(path = "/comments/{id}/{pageNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FeedComment>> getComments(@PathVariable("id") int feedId, @PathVariable("pageNo") int pageNo) throws Exception {
        List<FeedComment> commentsList = feedDataService.getFeedCommentsOnFeedId(feedId, pageNo);
        return new ResponseEntity<>(commentsList, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Get all the stats for the feeds."
    )
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Retrieves the stats for the feeds."
            ),
            @ApiResponse(
                    code = 400,
                    message = "the requested data is not found."
            )
    })
    @GetMapping(path = "/stats/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FeedStats> getFeedStats(@PathVariable("id") int feedId) {
        FeedStats statistic = feedDataService.getFeedDataStatsByFeedId(feedId);
        return new ResponseEntity<>(statistic, HttpStatus.OK);
    }


    @GetMapping(path = "/userProfile/myActivity/{pageNo}/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserActivity>> getUserActivityByEmail1(@PathVariable("pageNo") int pageNo, @PathVariable("email") String userEmail) {
        List  <UserActivity> userData = feedDataService.getAllDataByProfileEmail(pageNo,userEmail);
        return new ResponseEntity<>(userData, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Get Latest feeds statistic."
    )
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Retrieves the stats for the feeds."
            ),
            @ApiResponse(
                    code = 400,
                    message = "the requested data is not found."
            )
    })

    @GetMapping(path = "/statistic/{pageNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FeedStats>> getAllLatestFeedStats(@PathVariable("pageNo") int pageNo) {
        List<FeedStats> statsList = feedDataService.getAllLatestFeedDataStats(pageNo);
        return new ResponseEntity<>(statsList, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Get list of all usernames for like."
    )
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Retrieves the list of users for like."
            ),
            @ApiResponse(
                    code = 400,
                    message = "the requested data is not found."
            )
    })
    @GetMapping(path = "/like/usernames/{feedId}/{pageNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FeedLike>> getAllUsernameForLike(@PathVariable("feedId") int feedId, @PathVariable("pageNo") int pageNo) {
        List<FeedLike> listOfUsername = feedLikeService.displayAllLikeUsername(pageNo, feedId);
        return new ResponseEntity<>(listOfUsername, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Get list of all usernames for dislike."
    )
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Retrieves the list of users for dislike."
            ),
            @ApiResponse(
                    code = 400,
                    message = "the requested data is not found."
            )
    })
    @GetMapping(path = "/dislike/usernames/{feedId}/{pageNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FeedLike>> getAllUsernameForDisLike(@PathVariable("feedId") int feedId, @PathVariable("pageNo") int pageNo) {
        List<FeedLike> listOfUsername = feedLikeService.displayAllDisLikeUsername(pageNo, feedId);
        return new ResponseEntity<>(listOfUsername, HttpStatus.OK);
    }
}
