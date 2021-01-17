package io.zmyzheng.restapi.service;

import io.zmyzheng.restapi.domain.HotTopic;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.util.Date;
import java.util.List;

/**
 * @Author Mingyang Zheng
 * @Date 2021-01-16 22:30
 * @Version 3.0.0
 */
public interface HotTopicService {
    List<HotTopic> filterHotTopics(Date timeFrom, Date timeTo, GeoPoint center, String radius, int topN);
}
