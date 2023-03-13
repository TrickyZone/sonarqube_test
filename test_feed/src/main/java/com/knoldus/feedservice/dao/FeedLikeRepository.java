package com.knoldus.feedservice.dao;

import com.knoldus.feedservice.model.FeedLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository

public interface FeedLikeRepository extends JpaRepository<FeedLike, Integer> {

    FeedLike findByEmailAndFeedId(String email, int feedId);

    @Query("select userName, email from FeedLike fl where fl.likes = true and fl.feedId = :feedId")
    Page<FeedLike> findUserNameForLikesByFeedId(Pageable pageable, int feedId);

    @Query("select userName, email from FeedLike fl where fl.dislike = true and fl.feedId = :feedId ")
    Page<FeedLike> findUserNameForDisLikesByFeedId(Pageable pageable, int feedId);

    @Query("select count(likes) from FeedLike fl2 where fl2.likes = true and fl2.feedId = :feedId")
    int totalNoOfLikesByFeedId(int feedId);

    @Query("select count(dislike) from FeedLike fl3 where fl3.dislike = true and fl3.feedId = :feedId")
    int totalNoOfDislikesByFeedId(int feedId);
}
