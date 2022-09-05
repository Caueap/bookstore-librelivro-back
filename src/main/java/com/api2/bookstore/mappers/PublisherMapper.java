package com.api2.bookstore.mappers;

import com.api2.bookstore.dtos.publisherdto.PublisherDto;
import com.api2.bookstore.models.PublisherModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PublisherMapper {

    PublisherMapper INSTANCE = Mappers.getMapper(PublisherMapper.class);

    PublisherModel toModel(PublisherDto publisherDto);

    PublisherDto toDTO(PublisherModel publisherModel);
}
