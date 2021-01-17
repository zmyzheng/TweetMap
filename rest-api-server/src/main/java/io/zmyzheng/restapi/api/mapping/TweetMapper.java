package io.zmyzheng.restapi.api.mapping;

import io.zmyzheng.restapi.api.model.TweetDTO;
import io.zmyzheng.restapi.domain.Tweet;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Author Mingyang Zheng
 * @Date 2021-01-10 23:58
 * @Version 3.0.0
 */

@Mapper
public interface TweetMapper {
    TweetMapper INSTANCE = Mappers.getMapper(TweetMapper.class);

    TweetDTO convert(Tweet tweet);
    List<TweetDTO> convert(List<Tweet> tweets);
}
