package io.zmyzheng.restapi.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
