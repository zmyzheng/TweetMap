package io.zmyzheng.collector.implementation;

import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.Hosts;
import com.twitter.hbc.core.HttpHosts;
import com.twitter.hbc.core.endpoint.StatusesSampleEndpoint;
import com.twitter.hbc.core.endpoint.StreamingEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;
import io.zmyzheng.collector.SocialMediaCollector;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author Mingyang Zheng
 * @Date 2021-01-02 20:12
 * @Version 3.0.0
 */
@Slf4j
public class TweetCollector implements SocialMediaCollector<String> {

    private BlockingQueue<String> msgQueue;

    private Client twitterClient;

    public TweetCollector(String apiKey, String apiSecret, String token, String secret) {
        /** Set up your blocking queues: Be sure to size these properly based on expected TPS of your stream */
        this.msgQueue = new LinkedBlockingQueue<String>(1000);
        this.twitterClient = createTwitterClient(msgQueue, apiKey, apiSecret, token, secret);
    }

    private Client createTwitterClient(BlockingQueue<String> msgQueue, String apiKey, String apiSecret, String token, String secret){

        /** Declare the host you want to connect to, the endpoint, and authentication (basic auth or oauth) */
        Hosts hosebirdHosts = new HttpHosts(Constants.STREAM_HOST);
        StreamingEndpoint endpoint = new StatusesSampleEndpoint();

//        hosebirdEndpoint.trackTerms(terms);
        // These secrets should be read from a config file
        Authentication hosebirdAuth = new OAuth1(apiKey, apiSecret, token, secret);

        ClientBuilder builder = new ClientBuilder()
                .name("Hosebird-Client-01")                              // optional: mainly for the logs
                .hosts(hosebirdHosts)
                .authentication(hosebirdAuth)
                .endpoint(endpoint)
                .processor(new StringDelimitedProcessor(msgQueue));

        Client hosebirdClient = builder.build();
        return hosebirdClient;
    }

    @Override
    public void start() {
        twitterClient.connect();
    }

    @Override
    public String collect() {
        String msg = null;
        try {
            msg = msgQueue.poll(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        return msg;
    }

    @Override
    public void stop() {
        log.info("shutting down client from twitter...");
        this.twitterClient.stop();
        log.info("shutting down client from twitter: done!");
    }
}
