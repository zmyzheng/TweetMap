package io.zmyzheng.restapi.controller;

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

    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Tweet> getTweets(@RequestParam long timeFrom, @RequestParam long timeTo) {
        return this.tweetService.getTweets(timeFrom, timeTo);
    }


}
