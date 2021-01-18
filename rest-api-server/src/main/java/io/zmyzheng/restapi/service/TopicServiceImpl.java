package io.zmyzheng.restapi.service;

import io.zmyzheng.restapi.domain.HotTopicsTrend;
import io.zmyzheng.restapi.domain.TopicStatistic;
import io.zmyzheng.restapi.domain.TopicTrend;
import io.zmyzheng.restapi.repository.EsOperationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author Mingyang Zheng
 * @Date 2021-01-17 18:34
 * @Version 3.0.0
 */

@Service
@Slf4j
public class TopicServiceImpl implements TopicService {

    private final EsOperationRepository esOperationRepository;

    public TopicServiceImpl(EsOperationRepository esOperationRepository) {
        this.esOperationRepository = esOperationRepository;
    }

    @Override
    public List<TopicStatistic> filterTopics(Date timeFrom, Date timeTo, GeoPoint center, String radius, int topN) {
        return this.esOperationRepository.filterTopics(timeFrom, timeTo, center, radius, topN);
    }

    @Override
    public List<TopicTrend> getTopicTrend(String topicName, String calendarInterval, Date timeFrom, Date timeTo, GeoPoint center, String radius) {
        return this.esOperationRepository.getTopicTrend(topicName, calendarInterval, timeFrom, timeTo, center, radius);
    }

    @Override
    public List<HotTopicsTrend> getHotTopicsTrend(String calendarInterval, int topN, Date timeFrom, Date timeTo, GeoPoint center, String radius) {
        return this.esOperationRepository.getHotTopicsTrend(calendarInterval, topN, timeFrom, timeTo, center, radius);
    }
}
