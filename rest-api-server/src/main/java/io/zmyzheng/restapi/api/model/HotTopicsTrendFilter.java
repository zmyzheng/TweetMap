package io.zmyzheng.restapi.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.util.Date;

/**
 * @Author Mingyang Zheng
 * @Date 2021-01-17 20:25
 * @Version 3.0.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotTopicsTrendFilter {

    private String calendarInterval;
    private int topN;

    private Date timeFrom;
    private Date timeTo;
    private GeoPoint center;
    private String radius;
}
