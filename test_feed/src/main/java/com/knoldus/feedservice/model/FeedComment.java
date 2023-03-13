package com.knoldus.feedservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Entity
@Table(name = "Feed_Comments")
public class FeedComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_comment_id")
    int feedCommentId;

    @Column(name = "email")
    @Email(message = "email should be a valid email")
    @NotBlank(message = "Email is mandatory")
    String email;

    @Column(name = "comment")
    @NotBlank(message = "Comment is mandatory")
    String comment;

    @Column(name = "feed_time")
    @NotNull(message = "Feed Comment Time is mandatory")
    private Date feedTime;

    @Column(name = "user_name")
    @NotBlank(message = "Username is mandatory")
    private String userName;

    @Column(name = "feed_Id", insertable = false, updatable = false)
    private int feedId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "feed_id", nullable = false)
    private FeedData feedData;
}
