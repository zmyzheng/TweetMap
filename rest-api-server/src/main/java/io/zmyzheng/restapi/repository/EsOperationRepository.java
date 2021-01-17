package io.zmyzheng.restapi.repository;

import io.zmyzheng.restapi.api.model.TrendRequest;
import io.zmyzheng.restapi.domain.Tweet;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ResultsExtractor;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.elasticsearch.index.query.QueryBuilders.rangeQuery;
import static org.elasticsearch.search.aggregations.AggregationBuilders.range;
import static org.elasticsearch.search.aggregations.AggregationBuilders.terms;

/**
 * @Author: Mingyang Zheng
 * @Date: 2020-02-23 14:09
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

    public List<Tweet> filterTweets(Date timeFrom, Date timeTo, List<String> selectedTags, GeoPoint center, String radius) {
        Criteria criteria = new Criteria("timestamp").greaterThanEqual(timeFrom).lessThanEqual(timeTo)
                .and("hashTags").in(selectedTags)
                .and("coordinate").within(center, radius);
        Query query = new CriteriaQuery(criteria);
        return this.elasticsearchRestTemplate.search(query, Tweet.class)
                .get()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

    public List<? extends Terms.Bucket> aggregateByField(TrendRequest trendRequest, String field) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(rangeQuery("timestamp").gte(trendRequest.getTimeFrom()).lte(trendRequest.getTimeTo()))
                .withIndices("streaming").withTypes("tweets")
                .addAggregation(terms(field).field(field).size(trendRequest.getTopN()))
                .build();
        List<? extends Terms.Bucket> buckets = elasticsearchRestTemplate.query(searchQuery, new ResultsExtractor<List<? extends Terms.Bucket>>() {
            @Override
            public List<? extends Terms.Bucket> extract(SearchResponse response) {
                return  ((Terms) response.getAggregations().get(field)).getBuckets();
            }
        });
        return buckets;
    }

}
