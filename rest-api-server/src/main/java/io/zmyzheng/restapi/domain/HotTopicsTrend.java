package io.zmyzheng.restapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @Author Mingyang Zheng
 * @Date 2021-01-17 19:02
 * @Version 3.0.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotTopicsTrend {
    private Date date;
    private List<TopicStatistic> hotTopics;
}
