package io.zmyzheng.restapi.service;

import io.zmyzheng.restapi.api.model.Trend;
import io.zmyzheng.restapi.api.model.TrendRequest;
import io.zmyzheng.restapi.domain.Tweet;
import io.zmyzheng.restapi.repository.EsOperationRepository;
import io.zmyzheng.restapi.repository.TweetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Mingyang Zheng
 * @Date: 2020-02-22 19:31
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
    public List<Tweet> filterTweets(Date timeFrom, Date timeTo, List<String> selectedTags, List<Double> center, double radius) {
        return null;
    }

    @Override
    public List<Tweet> getTweets(long timeFrom, long timeTo) {
        return this.tweetRepository.findByTimestampBetween(timeFrom, timeTo);
    }

    @Override
    public List<Trend> queryTrends(TrendRequest trendRequest) {
        return this.esOperationRepository.aggregateByField(trendRequest, "hashTags")
                .stream()
                .map(bucket -> new Trend(bucket.getKeyAsString(), bucket.getDocCount()))
                .collect(Collectors.toList());
    }
}
