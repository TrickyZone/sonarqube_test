package com.knoldus.feedservice.controller;

import com.knoldus.feedservice.model.FeedData;
import com.knoldus.feedservice.service.FeedDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/feed-data")
@Api("FeedData API endpoints which are used for CRUD actions on the feeds.")
public class FeedDataController {
    @Autowired
    private FeedDataService feedDataService;

    @ApiOperation(
            value = "Get all the latest feed data."
    )
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Retrieves the latest data of feeds."
            ),
            @ApiResponse(
                    code = 400,
                    message = "the requested data is not found."
            )
    })

    @GetMapping("/get/{pageNo}")
    public List<FeedData> getFeedData(@PathVariable("pageNo") int pageNo) {
        return feedDataService.getFeedData(pageNo);
    }

    @GetMapping("/get/{id}")
    public Optional<FeedData> getFeedDataById(@PathVariable("id") int feedId,@PathVariable("pageNo") int pageNo) {
        return feedDataService.getFeedDataByFeedId(feedId);
    }

    @ApiOperation(
            value = "save the feed data into the database."
    )
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Data is saved"
            ),
            @ApiResponse(
                    code = 400,
                    message = "Error while saving the data."
            )
    })


    @PostMapping("/post")
    public FeedData postFeedData(@RequestBody FeedData feedData) {
        return feedDataService.postFeedData(feedData);
    }


    @ApiOperation(
            value = "Delete all the feed data."
    )
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Successfully deleted the feed data"
            ),
            @ApiResponse(
                    code = 400,
                    message = "request is not found."
            )
    })

    @DeleteMapping("/delete")
    public void deleteFeedData() {
        feedDataService.deleteFeedData();
    }

    @ApiOperation(
            value = "Update the feed data."
    )
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Successfully updated the data"
            ),
            @ApiResponse(
                    code = 400,
                    message = "Invalid request."
            )
    })


    @PutMapping("/update/{id}")
    public Optional<FeedData> updateFeedData(@PathVariable("id") int feedId, @RequestBody FeedData feedData) {
        return feedDataService.updateFeedData(feedId, feedData);
    }
}
