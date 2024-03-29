package org.mrtxee.bnksrs.accountservice.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.mrtxee.bnksrs.accountservice.dto.AccountDto;
import org.mrtxee.bnksrs.accountservice.model.Account;

@Mapper(componentModel = "spring")
public interface AccountDtoMapper {

    AccountDtoMapper MAPPER = Mappers.getMapper(AccountDtoMapper.class);

    @Mapping(target = "rec", source = "id")
    AccountDto toDto(Account account);

    @Mapping(source = "rec", target = "id")
    Account toEntity(AccountDto dto);

}
