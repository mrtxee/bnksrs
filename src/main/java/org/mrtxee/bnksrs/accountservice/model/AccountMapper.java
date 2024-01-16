package org.mrtxee.bnksrs.accountservice.model;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountMapper MAPPER = Mappers.getMapper( AccountMapper.class );

    @Mapping(target = "rec", source = "id")
    AccountDto toDto(Account account);

    @Mapping(source = "rec", target = "id")
    Account toEntity(AccountDto dto);

}
