package io.zmyzheng.restapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Mingyang Zheng
 * @Date 2021-01-16 22:22
 * @Version 3.0.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotTopic {
    private String tagName;
    private long count;
}
