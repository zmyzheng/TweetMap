package io.zmyzheng.restapi.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.util.Date;

/**
 * @Author: Mingyang Zheng
 * @Date: 2020-02-23 13:46
 * @Version 3.0.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicStatisticFilter {

    private int topN;

    private Date timeFrom;
    private Date timeTo;
    private GeoPoint center;
    private String radius;


}
