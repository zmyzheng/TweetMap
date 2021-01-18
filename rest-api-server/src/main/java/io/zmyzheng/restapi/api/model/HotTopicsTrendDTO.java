package io.zmyzheng.restapi.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @Author Mingyang Zheng
 * @Date 2021-01-17 20:28
 * @Version 3.0.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotTopicsTrendDTO {
    private Date date;
    private List<TopicStatisticDTO> hotTopics;
}
