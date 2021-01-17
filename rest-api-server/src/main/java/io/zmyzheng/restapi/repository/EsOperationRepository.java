package io.zmyzheng.restapi.repository;

import io.zmyzheng.restapi.domain.HotTopic;
import io.zmyzheng.restapi.domain.Tweet;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Mingyang Zheng
 * @Date: 2020-02-23 14:09
 * @Version 3.0.0
 *
 * @Description: this class defines operations that are not covered in standard Spring Data Repositories
 */

@Slf4j
@Repository
public class EsOperationRepository {

    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    public EsOperationRepository(ElasticsearchRestTemplate elasticsearchRestTemplate) {
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
    }

    private Criteria createFilterCriteria(Date timeFrom, Date timeTo, List<String> selectedTags, GeoPoint center, String radius) {
        Criteria criteria = new Criteria("timestamp").greaterThanEqual(timeFrom).lessThanEqual(timeTo);
        if (selectedTags != null) {
            criteria.and("hashTags").in(selectedTags);
        }
        if (center != null) {
            criteria.and("coordinate").within(center, radius);
        }
        return criteria;
    }

    public List<Tweet> filterTweets(Date timeFrom, Date timeTo, List<String> selectedTags, GeoPoint center, String radius) {
        Criteria criteria = createFilterCriteria(timeFrom, timeTo, selectedTags, center, radius);
        Query query = new CriteriaQuery(criteria);
        return this.elasticsearchRestTemplate.search(query, Tweet.class)
                .get()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }


    public List<HotTopic> filterHotTopics(Date timeFrom, Date timeTo, GeoPoint center, String radius, int topN) {
        BoolQueryBuilder builder = QueryBuilders.boolQuery()
                .filter(QueryBuilders.rangeQuery("timestamp").gte(timeFrom).lte(timeTo));

        if (center != null) {
            builder.must(QueryBuilders.geoDistanceQuery("coordinate").distance(radius));
        }

        Query query = new NativeSearchQueryBuilder()
                .withQuery(builder)
                .addAggregation(AggregationBuilders.terms("topics").field("hashTags").size(topN))
                .build();

        return this.elasticsearchRestTemplate.search(query, Tweet.class)
                .getAggregations()
                .<Terms>get("topics")
                .getBuckets()
                .stream()
                .map(bucket -> new HotTopic(bucket.getKeyAsString(), bucket.getDocCount()))
                .collect(Collectors.toList());




    }



}
