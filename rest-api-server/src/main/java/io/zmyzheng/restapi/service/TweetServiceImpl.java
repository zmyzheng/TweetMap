package io.zmyzheng.restapi.service;

import io.zmyzheng.restapi.domain.Tweet;
import io.zmyzheng.restapi.repository.TweetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Mingyang Zheng
 * @Date: 2020-02-22 19:31
 */
@Service
@Slf4j
public class TweetServiceImpl implements TweetService {

    private final TweetRepository tweetRepository;

    public TweetServiceImpl(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    @Override
    public List<Tweet> getTweets(long timeFrom, long timeTo) {
        return this.tweetRepository.findByTimestampBetween(timeFrom, timeTo);
    }
}
