package io.zmyzheng.restapi.service;

import io.zmyzheng.restapi.api.model.Trend;
import io.zmyzheng.restapi.api.model.TrendRequest;
import io.zmyzheng.restapi.domain.Tweet;
import io.zmyzheng.restapi.repository.EsOperationRepository;
import io.zmyzheng.restapi.repository.TweetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Mingyang Zheng
 * @Date: 2020-02-22 19:31
 * @Version 3.0.0
 */
@Service
@Slf4j
public class TweetServiceImpl implements TweetService {

    private final TweetRepository tweetRepository;
    private final EsOperationRepository esOperationRepository;

    public TweetServiceImpl(TweetRepository tweetRepository, EsOperationRepository esOperationRepository) {
        this.tweetRepository = tweetRepository;
        this.esOperationRepository = esOperationRepository;
    }

    @Override
    public List<Tweet> getTweets() {
        return this.tweetRepository.findAll();
    }

    @Override
    public List<Tweet> filterTweets(Date timeFrom, Date timeTo, List<String> selectedTags, GeoPoint center, String radius) {
        return this.esOperationRepository.filterTweets(timeFrom, timeTo, selectedTags, center, radius);
    }

    @Override
    public List<Tweet> getTweets(long timeFrom, long timeTo) {
        return this.tweetRepository.findByTimestampBetween(timeFrom, timeTo);
    }

    @Override
    public List<Trend> queryTrends(TrendRequest trendRequest) {
        return null;
    }
}
