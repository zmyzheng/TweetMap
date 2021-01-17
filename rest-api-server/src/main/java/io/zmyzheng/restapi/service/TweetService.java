package io.zmyzheng.restapi.service;

import io.zmyzheng.restapi.api.model.Trend;
import io.zmyzheng.restapi.api.model.TrendRequest;
import io.zmyzheng.restapi.domain.Tweet;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.util.Date;
import java.util.List;

/**
 * @Author: Mingyang Zheng
 * @Date: 2020-02-17 17:54
 * @Version 3.0.0
 */
public interface TweetService {

    List<Tweet> getTweets();

    List<Tweet> filterTweets(Date timeFrom, Date timeTo, List<String> selectedTags, GeoPoint center, String radius);

    List<Tweet> getTweets(long timeFrom, long timeTo);

    List<Trend> queryTrends(TrendRequest trendRequest);
}
