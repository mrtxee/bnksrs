package org.mrtxee.bnksrs.clientservcie.model;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientMapper MAPPER = Mappers.getMapper( ClientMapper.class );

    @Mapping(target = "rec", source = "id")
    ClientDto toClientDto(Client client);

    @Mapping(source = "rec", target = "id")
    Client toClient(ClientDto dto);

}
