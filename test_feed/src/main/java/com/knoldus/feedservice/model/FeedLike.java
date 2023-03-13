package com.knoldus.feedservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "Feed_Like")
public class FeedLike {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "feed_like_id")
    private int feedLikeId;

    @Column(name = "email")
    @Email(message = "email should be a valid email")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Column(name = "likes")
    private Boolean likes;

    @Column(name = "dislike")
    private Boolean dislike;

    @Column(name = "feed_Id", insertable = false, updatable = false)
    private int feedId;

    @Column(name = "user_name")
    @NotBlank(message = "Username is mandatory")
    private String userName;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "feed_id", nullable = false)
    private FeedData feedData;

    @Column(name="feed_time")
    private Date feedTime;
}
