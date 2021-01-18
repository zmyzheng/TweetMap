package io.zmyzheng.restapi.api.mapping;

import io.zmyzheng.restapi.api.model.HotTopicsTrendDTO;
import io.zmyzheng.restapi.domain.HotTopicsTrend;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Author Mingyang Zheng
 * @Date 2021-01-17 20:30
 * @Version 3.0.0
 */

@Mapper
public interface HotTopicsTrendMapper {

    HotTopicsTrendMapper INSTANCE = Mappers.getMapper(HotTopicsTrendMapper.class);

    List<HotTopicsTrendDTO> convert(List<HotTopicsTrend> topicStatistic);
}
