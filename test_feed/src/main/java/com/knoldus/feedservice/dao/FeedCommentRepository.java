package com.knoldus.feedservice.dao;

import com.knoldus.feedservice.model.FeedComment;
import com.knoldus.feedservice.model.FeedData;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedCommentRepository extends JpaRepository<FeedComment, Integer> {

    List<FeedComment> findByFeedDataOrderByFeedCommentIdDesc(FeedData feedData, Pageable pageable);

    @Query("select count(comment) from FeedComment fl4 where fl4.feedId = :feedId")
    int totalNoOfCommentsByFeedId(int feedId);
}
