package io.zmyzheng.restapi.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.util.Date;
import java.util.List;

/**
 * @Author Mingyang Zheng
 * @Date 2021-01-12 23:01
 * @Version 3.0.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TweetFilter {
    private Date timeFrom;
    private Date timeTo;
    private List<String> selectedTags;
    private GeoPoint center;
    private String radius;
}
