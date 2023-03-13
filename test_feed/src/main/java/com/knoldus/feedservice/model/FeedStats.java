package com.knoldus.feedservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class FeedStats {
    private int feedId;
    private int totalNumberOfLikes;
    private int totalNumberOfDislikes;
    private int totalNumberOfComments;
}
