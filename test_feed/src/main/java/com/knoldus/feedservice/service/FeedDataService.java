package com.knoldus.feedservice.service;

import com.knoldus.feedservice.dao.FeedCommentRepository;
import com.knoldus.feedservice.dao.FeedDataRepository;
import com.knoldus.feedservice.dao.FeedLikeRepository;
import com.knoldus.feedservice.exception.RecordNotFoundException;
import com.knoldus.feedservice.model.FeedComment;
import com.knoldus.feedservice.model.FeedData;
import com.knoldus.feedservice.model.FeedStats;
import com.knoldus.feedservice.model.UserActivity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

import static com.knoldus.feedservice.util.Constants.*;
import static com.knoldus.feedservice.util.TimeDifference.getTimeDifference;

@Service
@Slf4j
public class FeedDataService {
    private static final Integer DURATION = 60;
    private static final Integer DAYS = 1;
    @Autowired
    private FeedDataRepository feedDataRepository;

    @Autowired
    private FeedLikeRepository feedLikeRepository;
    @Autowired
    private FeedCommentRepository feedCommentRepository;
    @Autowired
    Environment environment;


    public List<FeedData> getFeedData(int pageNo) {
        int pageSize = Integer.parseInt(Objects.requireNonNull(environment.getProperty(PAGE_SIZE_KEY)));
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, FEED_TIME_KEY);
        Date currentdate = new Date();
        return feedDataRepository.findAll(pageable)
                .stream().peek(feedData -> feedData.setLapseTimeDifference(getTimeDifference(currentdate, feedData.getFeedTime()))).collect(Collectors.toList());
    }

    public List<FeedData> getFeedDataWithTenantId(String tenantId, int pageNo) {
        int pageSize = Integer.parseInt(Objects.requireNonNull(environment.getProperty(PAGE_SIZE_KEY)));
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, FEED_TIME_KEY);
        Date endDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)-6);
        Date startDate = cal.getTime();

        return feedDataRepository.findAllByTenantIdAndFeedTimeBetween(tenantId, startDate, endDate,pageable)
                .stream().peek(feedData -> feedData.setLapseTimeDifference(getTimeDifference(endDate, feedData.getFeedTime()))).collect(Collectors.toList());
    }

    public Optional<FeedData> getFeedDataByFeedId(int feedId) {
        return feedDataRepository.findById(feedId);
    }

    public List<FeedComment> getFeedCommentsOnFeedId(int feedId, int pageNo) {
        FeedData data = feedDataRepository.findById(feedId).orElseThrow(() -> new RecordNotFoundException("FeedId not found"));
        int pageSize = Integer.parseInt(Objects.requireNonNull(environment.getProperty(PAGE_SIZE_KEY)));
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, FEED_TIME_KEY);
        List<FeedComment> data1 = feedCommentRepository.findByFeedDataOrderByFeedCommentIdDesc(data, pageable);
        return data1;
    }

    public FeedStats getFeedDataStatsByFeedId(int feedId) {
        FeedStats feedStats = new FeedStats(feedId, feedLikeRepository.totalNoOfLikesByFeedId(feedId), feedLikeRepository.totalNoOfDislikesByFeedId(feedId), feedCommentRepository.totalNoOfCommentsByFeedId(feedId));
        return feedStats;
    }


    public FeedData postFeedData(FeedData feedData) {
        return feedDataRepository.save(feedData);
    }

    public void deleteFeedData() {
        feedDataRepository.deleteAll();
    }

    public Optional<FeedData> updateFeedData(int id, FeedData feedData) {
        Optional<FeedData> feedLikeRepositoryById = feedDataRepository.findById(id);
        feedLikeRepositoryById.ifPresent(b -> b.setEmail(feedData.getEmail()));
        feedLikeRepositoryById.ifPresent(b -> b.setContributionType(feedData.getContributionType()));
        feedLikeRepositoryById.ifPresent(b -> b.setTitle(feedData.getTitle()));
        feedLikeRepositoryById.ifPresent(b -> b.setDescription(feedData.getDescription()));
        feedLikeRepositoryById.ifPresent(b -> b.setFeedTime(feedData.getFeedTime()));
        feedLikeRepositoryById.ifPresent(b -> b.setUrl(feedData.getUrl()));
        feedLikeRepositoryById.ifPresent(b -> feedDataRepository.save(b));
        return feedDataRepository.findById(id);
    }

    public List<FeedStats> getAllLatestFeedDataStats(int pageNo) {
        int pageSize = Integer.parseInt(environment.getProperty(PAGE_SIZE_KEY));
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, FEED_ID_KEY);
        List<FeedStats> feedStats = new ArrayList<>();
        Page<FeedData> data = feedDataRepository.findAll(pageable);
        List<FeedData> list = data.toList();
        for (int k = 0; k < list.size(); k++) {
            int feedId = list.get(k).getFeedId();
            feedStats.add(new FeedStats(feedId, feedLikeRepository.totalNoOfLikesByFeedId(feedId), feedLikeRepository.totalNoOfDislikesByFeedId(feedId), feedCommentRepository.totalNoOfCommentsByFeedId(feedId)));
        }
        Page<FeedStats> pages = new PageImpl<>(feedStats, pageable, feedStats.size());
        return pages.toList();
    }


    public List<UserActivity> getAllDataByProfileEmail(int pageNo, String userEmail) {
        int pageSize = Integer.parseInt(environment.getProperty(PAGE_SIZE_KEY));
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, FEED_TIME_KEY);
        return getDataFromDifferentTable ( pageable,userEmail);
    }

        private List<UserActivity> getDataFromDifferentTable (Pageable pageable, String userEmail){
            Date currentdate = new Date();
            return feedDataRepository.findbyUserEmailId(userEmail).stream().peek(userActivity -> userActivity.setLapseTimeDifference(Objects.nonNull(userActivity.getFeedtime())?getTimeDifference(currentdate,userActivity.getFeedtime()):null)).collect(Collectors.toList());
        }

    }



