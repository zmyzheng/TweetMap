package io.zmyzheng.restapi.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
