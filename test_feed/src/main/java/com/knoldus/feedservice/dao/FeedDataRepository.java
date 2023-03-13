package com.knoldus.feedservice.dao;

import com.knoldus.feedservice.model.FeedData;
import com.knoldus.feedservice.model.UserActivity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Date;
import java.util.List;


public interface FeedDataRepository extends JpaRepository<FeedData, Integer> {
    Page<FeedData> findAll(Pageable pageable);

    Page<FeedData> findByTenantId(String tenantId,Pageable pageable);

    @Query(name = "find_details_for_myActivity",nativeQuery = true)
    List<UserActivity> findbyUserEmailId(@Param("userEmail") String userEmail);



    Page<FeedData> findAllByTenantIdAndFeedTimeBetween( @Param("tenantId") String tenantId,  @Param("startDate") Date startDate,  @Param("endDate") Date endDate, @Param("pageable") Pageable pageable);
}
