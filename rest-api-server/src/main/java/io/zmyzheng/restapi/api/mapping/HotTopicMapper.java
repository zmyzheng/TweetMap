package io.zmyzheng.restapi.api.mapping;

import io.zmyzheng.restapi.api.model.HotTopicDTO;
import io.zmyzheng.restapi.domain.HotTopic;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @Author Mingyang Zheng
 * @Date 2021-01-16 22:26
 * @Version 3.0.0
 */
@Mapper
public interface HotTopicMapper {
    HotTopicMapper INSTANCE = Mappers.getMapper(HotTopicMapper.class);

    @Mapping(source = "tagName", target = "topicName")
    HotTopicDTO convert(HotTopic hotTopic);
}
