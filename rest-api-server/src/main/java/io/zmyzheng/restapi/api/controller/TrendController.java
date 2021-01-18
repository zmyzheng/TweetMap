package io.zmyzheng.restapi.api.controller;

import io.zmyzheng.restapi.api.mapping.TopicTrendMapper;
import io.zmyzheng.restapi.api.model.TopicTrendDTO;
import io.zmyzheng.restapi.api.model.TopicTrendFilter;
import io.zmyzheng.restapi.service.TopicTrendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Mingyang Zheng
 * @Date 2021-01-17 15:58
 * @Version 3.0.0
 */

@Slf4j
@RestController
@RequestMapping(TrendController.BASE_URL)
public class TrendController {

    public static final String BASE_URL = "api/v3/trends";

    private final TopicTrendService topicTrendService;
    private final TopicTrendMapper topicTrendMapper;

    public TrendController(TopicTrendService topicTrendService, TopicTrendMapper topicTrendMapper) {
        this.topicTrendService = topicTrendService;
        this.topicTrendMapper = topicTrendMapper;
    }

    @PostMapping("/topic")
    @ResponseStatus(HttpStatus.OK)
    public List<TopicTrendDTO> filterTrendByTopic(@RequestBody TopicTrendFilter topicTrendFilter) {
        return this.topicTrendMapper
                .convert(this.topicTrendService.getTopicTrend(topicTrendFilter.getTopicName(), topicTrendFilter.getCalendarInterval(), topicTrendFilter.getTimeFrom(), topicTrendFilter.getTimeTo(), topicTrendFilter.getCenter(), topicTrendFilter.getRadius()));
    }
}
