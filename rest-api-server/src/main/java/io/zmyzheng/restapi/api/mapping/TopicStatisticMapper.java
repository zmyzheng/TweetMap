package io.zmyzheng.restapi.api.mapping;

import io.zmyzheng.restapi.api.model.TopicStatisticDTO;
import io.zmyzheng.restapi.domain.TopicStatistic;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Author Mingyang Zheng
 * @Date 2021-01-16 22:26
 * @Version 3.0.0
 */
@Mapper
public interface TopicStatisticMapper {
    TopicStatisticMapper INSTANCE = Mappers.getMapper(TopicStatisticMapper.class);

    List<TopicStatisticDTO> convert(List<TopicStatistic> topicStatistic);
}
