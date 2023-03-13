package com.knoldus.feedservice.controller;

import com.knoldus.feedservice.dao.FeedDataRepository;
import com.knoldus.feedservice.dao.FeedLikeRepository;
import com.knoldus.feedservice.model.FeedLike;
import com.knoldus.feedservice.service.FeedLikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/feed-like")
@Api("FeedData API endpoints which are used for CRUD actions on the feed likes.")
public class FeedLikeController {

    @Autowired
    private FeedLikeService feedLikeService;
    @Autowired
    private FeedLikeRepository feedLikeRepository;
    @Autowired
    private FeedDataRepository feedDataRepository;

    @ApiOperation(
            value = "Get all the latest likes on data."
    )
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Retrieves the latest likes on the feeds."
            ),
            @ApiResponse(
                    code = 400,
                    message = "the requested data is not found."
            )
    })


    @GetMapping("/get")
    public List<FeedLike> getFeedLike() {
        return feedLikeService.getFeedLike();
    }

    @ApiOperation(
            value = "save the like data."
    )
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Likes in the the feeds is saved"
            ),
            @ApiResponse(
                    code = 400,
                    message = "Error while saving the data."
            )
    })

    @PostMapping("/post/{id}")
    @CrossOrigin
    public FeedLike postFeedLike(@PathVariable(value = "id") int feedId,
                                 @Valid @RequestBody FeedLike feedLike) {
        return feedLikeService.postFeedLike(feedId, feedLike);
    }

    @ApiOperation(
            value = "Delete all the likes."
    )
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Successfully deleted the like responses"
            ),
            @ApiResponse(
                    code = 400,
                    message = "request is not found."
            )
    })

    @DeleteMapping("/delete")
    public void deleteFeedLike() {
        feedLikeService.deleteFeedLike();
    }

    @ApiOperation(
            value = "Update the like data."
    )
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Successfully updated the likes"
            ),
            @ApiResponse(
                    code = 400,
                    message = "Invalid request."
            )
    })

    @PutMapping("/update/{id}")
    public FeedLike updateFeedLike(@PathVariable("id") int feedLikeId, @RequestBody FeedLike feedLike) {
        return feedLikeService.updateFeedLike(feedLikeId, feedLike);
    }
}
