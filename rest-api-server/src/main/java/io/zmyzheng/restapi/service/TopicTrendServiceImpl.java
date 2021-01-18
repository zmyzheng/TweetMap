package io.zmyzheng.restapi.service;

import io.zmyzheng.restapi.domain.TopicTrend;
import io.zmyzheng.restapi.repository.EsOperationRepository;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.util.Date;
import java.util.List;

/**
 * @Author Mingyang Zheng
 * @Date 2021-01-17 16:54
 * @Version 3.0.0
 */
public class TopicTrendServiceImpl implements TopicTrendService {

    private final EsOperationRepository esOperationRepository;

    public TopicTrendServiceImpl(EsOperationRepository esOperationRepository) {
        this.esOperationRepository = esOperationRepository;
    }


    @Override
    public List<TopicTrend> getTopicTrend(String topicName, String calendarInterval, Date timeFrom, Date timeTo, GeoPoint center, String radius) {
        return this.esOperationRepository.getTopicTrend(topicName, calendarInterval, timeFrom, timeTo, center, radius);
    }
}
