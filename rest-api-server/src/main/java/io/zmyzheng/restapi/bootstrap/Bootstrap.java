package io.zmyzheng.restapi.bootstrap;

import io.zmyzheng.restapi.domains.Tweet;
import io.zmyzheng.restapi.repositories.TweetRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

/**
 * @Author: Mingyang Zheng
 * @Date: 2020-02-17 01:06
 */
@Component
public class Bootstrap implements CommandLineRunner {

    private final TweetRepository tweetRepository;

    public Bootstrap(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        Tweet tweet = new Tweet(null, new Date(), Arrays.asList("love", "peace"), Arrays.asList(-48.87868, -23.97924));
        this.tweetRepository.save(tweet);

        System.out.println(this.tweetRepository.findById("1CYSVXABUOm3GmEwuy9F"));
    }
}
