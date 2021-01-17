package io.zmyzheng.restapi.api.controller;

import io.zmyzheng.restapi.api.mapping.HotTopicMapper;
import io.zmyzheng.restapi.api.model.HotTopicDTO;
import io.zmyzheng.restapi.api.model.HotTopicFilter;
import io.zmyzheng.restapi.service.HotTopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Mingyang Zheng
 * @Date 2021-01-16 21:57
 * @Version 3.0.0
 */

@Slf4j
@RestController
@RequestMapping(HotTopicController.BASE_URL)
public class HotTopicController {
    public static final String BASE_URL = "api/v3/hotTopics";

    private final HotTopicService hotTopicService;
    private final HotTopicMapper hotTopicMapper;

    public HotTopicController(HotTopicService hotTopicService, HotTopicMapper hotTopicMapper) {
        this.hotTopicService = hotTopicService;
        this.hotTopicMapper = hotTopicMapper;
    }

    @PostMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public List<HotTopicDTO> filterHotTopics(@RequestBody HotTopicFilter hotTopicFilter) {
        return this.hotTopicService
                .filterHotTopics(hotTopicFilter.getTimeFrom(), hotTopicFilter.getTimeTo(), hotTopicFilter.getCenter(), hotTopicFilter.getRadius(), hotTopicFilter.getTopN())
                .stream().map(this.hotTopicMapper::convert)
                .collect(Collectors.toList());
    }
}
