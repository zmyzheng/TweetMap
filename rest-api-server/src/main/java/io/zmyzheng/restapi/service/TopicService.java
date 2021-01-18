package io.zmyzheng.restapi.service;

import io.zmyzheng.restapi.domain.TopicStatistic;
import io.zmyzheng.restapi.domain.TopicTrend;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.util.Date;
import java.util.List;

/**
 * @Author Mingyang Zheng
 * @Date 2021-01-17 18:32
 * @Version 3.0.0
 */

public interface TopicService {
    List<TopicStatistic> filterTopics(Date timeFrom, Date timeTo, GeoPoint center, String radius, int topN);

    List<TopicTrend> getTopicTrend(String topicName, String calendarInterval, Date timeFrom, Date timeTo, GeoPoint center, String radius);


}
