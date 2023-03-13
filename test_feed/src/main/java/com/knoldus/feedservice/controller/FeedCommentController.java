package com.knoldus.feedservice.controller;

import com.knoldus.feedservice.dao.FeedDataRepository;
import com.knoldus.feedservice.model.FeedComment;
import com.knoldus.feedservice.service.FeedCommentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/feed-comment")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FeedCommentController {

    @Autowired
    private FeedCommentService feedCommentService;
    @Autowired
    private FeedDataRepository feedDataRepository;

    @ApiOperation(
            value = "Get all the latest comments on the feed data."
    )
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Retrieves the latest comments on the feeds."
            ),
            @ApiResponse(
                    code = 400,
                    message = "the requested data is not found."
            )
    })
    @GetMapping("/get")
    public List<FeedComment> getFeedComments() {
        return feedCommentService.getFeedComment();
    }

    @ApiOperation(
            value = "Get all the latest comments for particular feed id."
    )
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Retrieves the latest comments on the feeds."
            ),
            @ApiResponse(
                    code = 400,
                    message = "the requested data is not found."
            )
    })

    @GetMapping("/get/{id}")
    public Optional<FeedComment> getFeedCommentById(@PathVariable("id") int feedId) {
        return feedCommentService.getFeedCommentById(feedId);
    }


    @ApiOperation(
            value = "save the comment data."
    )
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Comments in the the feeds is saved"
            ),
            @ApiResponse(
                    code = 400,
                    message = "Error while saving the data."
            )
    })


    @PostMapping("/post/{id}")
    @CrossOrigin
    public FeedComment postFeedComment(@PathVariable(value = "id") int feedId,
                                       @Valid @RequestBody FeedComment feedComment) {
        return feedCommentService.postFeedComment(feedId, feedComment);
    }

    @ApiOperation(
            value = "Delete all the comments."
    )
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Successfully deleted the comments responses"
            ),
            @ApiResponse(
                    code = 400,
                    message = "request is not found."
            )
    })
    @DeleteMapping("/delete")
    public void deleteFeedComment() {
        feedCommentService.deleteFeedComment();
    }

    @ApiOperation(
            value = "Update the comments data."
    )
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Successfully updated the comments"
            ),
            @ApiResponse(
                    code = 400,
                    message = "Invalid request."
            )
    })
    @PutMapping("/update/{id}")
    public Optional<FeedComment> updateFeedComment(@PathVariable("id") int feedCommentId, @RequestBody FeedComment feedComment) {
        return feedCommentService.updateFeedComment(feedCommentId, feedComment);
    }
}
