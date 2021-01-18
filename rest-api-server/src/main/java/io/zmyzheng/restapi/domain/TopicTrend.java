package io.zmyzheng.restapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author Mingyang Zheng
 * @Date 2021-01-17 16:06
 * @Version 3.0.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicTrend {
    private Date date;
    private long count;
}
