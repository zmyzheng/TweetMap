package io.zmyzheng.restapi.api.mapping;


import io.zmyzheng.restapi.api.model.TopicTrendDTO;
import io.zmyzheng.restapi.domain.TopicTrend;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Author Mingyang Zheng
 * @Date 2021-01-17 17:11
 * @Version 3.0.0
 */

@Mapper
public interface TopicTrendMapper {
    TopicTrendMapper INSTANCE = Mappers.getMapper(TopicTrendMapper.class);

    List<TopicTrendDTO> convert(List<TopicTrend> topicTrends);
}
