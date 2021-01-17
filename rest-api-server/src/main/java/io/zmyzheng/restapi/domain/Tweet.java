package io.zmyzheng.restapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;


import java.util.Date;
import java.util.List;

/**
 * @Author: Mingyang Zheng
 * @Date: 2020-02-17 00:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "tweets")
public class Tweet {
    @Id
    private String id;

    @Field(name = "timestamp", type = FieldType.Date)
    private Date timestamp;

    @Field(name = "hashTags", type = FieldType.Keyword)
    private List<String> hashTags;

    @GeoPointField
    private GeoPoint coordinate;



}
