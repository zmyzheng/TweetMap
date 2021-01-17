package io.zmyzheng.restapi.service;

import io.zmyzheng.restapi.domain.HotTopic;
import io.zmyzheng.restapi.repository.EsOperationRepository;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.util.Date;
import java.util.List;

/**
 * @Author Mingyang Zheng
 * @Date 2021-01-16 23:46
 * @Version 3.0.0
 */
public class HotTopicServiceImpl implements HotTopicService {

    private final EsOperationRepository esOperationRepository;

    public HotTopicServiceImpl(EsOperationRepository esOperationRepository) {
        this.esOperationRepository = esOperationRepository;
    }

    @Override
    public List<HotTopic> filterHotTopics(Date timeFrom, Date timeTo, GeoPoint center, String radius, int topN) {
        return this.esOperationRepository.filterHotTopics(timeFrom, timeTo, center, radius, topN);
    }
}
