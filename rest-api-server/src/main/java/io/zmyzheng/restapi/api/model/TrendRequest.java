package io.zmyzheng.restapi.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Mingyang Zheng
 * @Date: 2020-02-23 13:46
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrendRequest {

    private long timeFrom;

    private long timeTo;

    private int topN;

}
