package com.api2.bookstore.mappers;

import com.api2.bookstore.dtos.clientdto.ClientDto;
import com.api2.bookstore.models.ClientModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    ClientModel toModel(ClientDto clientDto);

    ClientDto toDTO(ClientModel clientModel);
}
