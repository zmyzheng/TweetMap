package io.zmyzheng.restapi.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Mingyang Zheng
 * @Date 2021-01-16 22:25
 * @Version 3.0.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicStatisticDTO {
    private String topicName;
    private long count;
}
