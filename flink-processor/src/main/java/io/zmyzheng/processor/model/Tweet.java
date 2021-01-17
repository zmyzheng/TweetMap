package io.zmyzheng.processor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.elasticsearch.common.geo.GeoPoint;

import java.util.List;

/**
 * @Author: Mingyang Zheng
 * @Date: 2020-02-11 21:31
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tweet implements UniqueEntity<String> {

    private String id;

    private long timestamp;

    private List<String> hashTags;

    private GeoPoint coordinate;

    @Override
    public String getUniqueKey() {
        return getId();
    }
}
