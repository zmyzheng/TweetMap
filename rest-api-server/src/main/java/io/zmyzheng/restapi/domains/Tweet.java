package io.zmyzheng.restapi.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;


import java.util.Date;
import java.util.List;

/**
 * @Author: Mingyang Zheng
 * @Date: 2020-02-17 00:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "streaming", type = "tweets")
public class Tweet {
    @Id
    String id;

    @Field(name = "timestamp", type = FieldType.Date)
    Date timestamp;

    @Field(name = "hashTags", type = FieldType.Keyword)
    List<String> hashTags;

    @GeoPointField
    List<Double> coordinate;



}
