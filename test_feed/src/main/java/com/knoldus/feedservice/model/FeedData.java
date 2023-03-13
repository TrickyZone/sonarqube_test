package com.knoldus.feedservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@NamedNativeQuery(name = "find_details_for_myActivity", query = "select cc.Ctype as feedType,cc.username as username,cc.likes as likes,cc.dislike as dislike,cc.ContributionBy as contributedBy,cc.contributionType as contributionType,cc.description as description,cc.feedcomment as feedcomment,cc.title as title,cc.contributionOn as feedtime from  (select 'CONTRIBUTION' Ctype,fd.user_name username,null  likes,null dislike,fd.email ContributionBy,fd .contribution_type contributionType,fd.description description ,null feedcomment,fd.title title ,fd.feed_time contributionOn from feed_data fd where fd.email =:userEmail union select'LIKE&DISLIKE' Ctype ,fd.user_name  username,fl.likes  likes,fl.dislike  dislike,fl.email ContributionBy,fd .contribution_type contributionType,fd.description description ,null feedcomment,\n" +
        "fd.title title ,fl.feed_time contributionOn from feed_data fd,feed_like fl where fd.feed_id = fl.feed_id and fl.email = :userEmail AND fl.feed_time notnull  union select 'COMMENT' Ctype,fd.user_name  username,null  likes,null dislike,fc.email ContributionBy,fd .contribution_type contributionType,fd.description description ,fc.\"comment\" feedcomment,fd.title title ,fc.feed_time contributionOn from feed_data fd,feed_comments fc where fd.feed_id = fc.feed_id and fc.email = :userEmail) cc order by cc.contributionOn desc  ", resultSetMapping = "my_activity_dto")
@SqlResultSetMapping(name = "my_activity_dto", classes = @ConstructorResult(targetClass = UserActivity.class, columns = {
        @ColumnResult(name = "feedType", type = String.class),
        @ColumnResult(name = "username", type = String.class),
        @ColumnResult(name = "likes", type = Boolean.class),
        @ColumnResult(name = "dislike", type = Boolean.class),
        @ColumnResult(name = "contributedBy", type = String.class),
        @ColumnResult(name = "contributionType", type = String.class),
        @ColumnResult(name = "description", type = String.class),
        @ColumnResult(name = "feedcomment", type = String.class),
        @ColumnResult(name = "title", type = String.class),
        @ColumnResult(name = "feedtime", type = Date.class)
}))
@Table(name = "Feed_Data")
@ToString
public class FeedData {

    @Column
    @Id
    @GeneratedValue(generator="increment",strategy = GenerationType.SEQUENCE)
    private int feedId;

    @Column(name = "contribution_id")
    private String contributionId;

    @Column(name="user_name")
    @NotEmpty
    private String userName;

    @Column(name = "tenantId")
    private String tenantId;

    @Column(name = "email")
    @NotEmpty
    @Email
    private String email;

    @Column(name = "contribution_type")
    private String contributionType;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "feed_time")
    private Date feedTime;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String lapseTimeDifference;

    @Column(name = "url")
    private String url;

    @Column(name="user_xref")
    private int userXref;

    @Column(name="feed_type")
    private String feedType;

    @OneToMany(mappedBy = "feedData")
    private List<FeedLike> feedLike = new ArrayList<>();

    @OneToMany(mappedBy = "feedData")
    private List<FeedComment> feedComments = new ArrayList<>();
}
