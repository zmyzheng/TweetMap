package io.zmyzheng.restapi.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Mingyang Zheng
 * @Date: 2020-02-23 13:49
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrendResponse {

    private String tagName;
    private int frequency;
}
