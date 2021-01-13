package io.zmyzheng.restapi.api.controller;

import io.zmyzheng.restapi.api.model.TweetDTO;
import io.zmyzheng.restapi.api.mapping.TweetMapper;
import io.zmyzheng.restapi.api.model.Trend;
import io.zmyzheng.restapi.api.model.TrendRequest;
import io.zmyzheng.restapi.api.model.TweetFilter;
import io.zmyzheng.restapi.domain.Tweet;
import io.zmyzheng.restapi.service.TweetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Mingyang Zheng
 * @Date: 2020-02-17 17:53
 */

@Slf4j
@RestController
@RequestMapping(TweetController.BASE_URL)
public class TweetController {
    public static final String BASE_URL = "api/v1/tweets";

    private final TweetService tweetService;
    private final TweetMapper tweetMapper;

    public TweetController(TweetService tweetService, TweetMapper tweetMapper) {
        this.tweetService = tweetService;
        this.tweetMapper = tweetMapper;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TweetDTO> getTweets() {
        return this.tweetMapper.convert(this.tweetService.getTweets());
    }

    @PostMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public List<TweetDTO> filterTweets(@RequestBody  TweetFilter tweetFilter) {
        return this.tweetMapper.convert(this.tweetService.filterTweets(tweetFilter.getTimeFrom(), tweetFilter.getTimeTo(), tweetFilter.getSelectedTags(), tweetFilter.getCenter(), tweetFilter.getRadius()));
    }




}
