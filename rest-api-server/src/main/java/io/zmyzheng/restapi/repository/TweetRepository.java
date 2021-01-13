package io.zmyzheng.restapi.repository;

import io.zmyzheng.restapi.domain.Tweet;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Mingyang Zheng
 * @Date: 2020-02-17 00:51
 */

@Repository
public interface TweetRepository extends ElasticsearchRepository<Tweet, String> {
    List<Tweet> findAll();
    List<Tweet> findByTimestampBetween(long from, long to);
}
