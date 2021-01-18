package io.zmyzheng.restapi.api.controller;

import io.zmyzheng.restapi.api.mapping.TopicStatisticMapper;
import io.zmyzheng.restapi.api.mapping.TopicTrendMapper;
import io.zmyzheng.restapi.api.model.TopicStatisticFilter;
import io.zmyzheng.restapi.api.model.TopicStatisticDTO;
import io.zmyzheng.restapi.api.model.TopicTrendDTO;
import io.zmyzheng.restapi.api.model.TopicTrendFilter;
import io.zmyzheng.restapi.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Mingyang Zheng
 * @Date 2021-01-17 18:39
 * @Version 3.0.0
 */

@Slf4j
@RestController
@RequestMapping(TopicController.BASE_URL)
public class TopicController {
    public static final String BASE_URL = "api/v3/topics";

    private final TopicService topicService;
    private final TopicStatisticMapper topicStatisticMapper;
    private final TopicTrendMapper topicTrendMapper;

    public TopicController(TopicService topicService, TopicStatisticMapper topicStatisticMapper, TopicTrendMapper topicTrendMapper) {
        this.topicService = topicService;
        this.topicStatisticMapper = topicStatisticMapper;
        this.topicTrendMapper = topicTrendMapper;
    }

    @PostMapping("/statistic")
    @ResponseStatus(HttpStatus.OK)
    public List<TopicStatisticDTO> getTopicStatistic(@RequestBody TopicStatisticFilter topicStatisticFilter) {
        return this.topicStatisticMapper
                .convert(this.topicService.filterTopics(topicStatisticFilter.getTimeFrom(), topicStatisticFilter.getTimeTo(), topicStatisticFilter.getCenter(), topicStatisticFilter.getRadius(), topicStatisticFilter.getTopN()));
    }

    @PostMapping("/{topicName}/trend")
    @ResponseStatus(HttpStatus.OK)
    public List<TopicTrendDTO> filterTrendByTopic(@RequestParam String topicName, @RequestBody TopicTrendFilter topicTrendFilter) {
        return this.topicTrendMapper
                .convert(this.topicService.getTopicTrend(topicName, topicTrendFilter.getCalendarInterval(), topicTrendFilter.getTimeFrom(), topicTrendFilter.getTimeTo(), topicTrendFilter.getCenter(), topicTrendFilter.getRadius()));
    }
}
