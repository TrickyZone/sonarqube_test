package com.knoldus.feedservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class UserActivity {
    private String feedType;
    private String username;
    private Boolean likes;
    private Boolean dislike;
    private String contributedBy;

    private String contributionType;

    private String description;

    private String feedcomment;

    private String title;

    private Date feedtime;

   private String lapseTimeDifference;

    public UserActivity(String feedType, String username, Boolean likes, Boolean dislike, String contributedBy, String contributionType, String description, String feedcomment, String title, Date feedtime) {
        this.feedType = feedType;
        this.username = username;
        this.likes = likes;
        this.dislike = dislike;
        this.contributedBy = contributedBy;
        this.contributionType = contributionType;
        this.description = description;
        this.feedcomment = feedcomment;
        this.title = title;
        this.feedtime = feedtime;
    }
}

