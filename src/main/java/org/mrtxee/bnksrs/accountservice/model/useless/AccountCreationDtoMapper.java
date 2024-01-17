package org.mrtxee.bnksrs.accountservice.model.useless;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.mrtxee.bnksrs.accountservice.model.Account;
import org.mrtxee.bnksrs.accountservice.model.AccountDto;

@Mapper(componentModel = "spring")
public interface AccountCreationDtoMapper {

    AccountCreationDtoMapper MAPPER = Mappers.getMapper( AccountCreationDtoMapper.class );

//    @Mapping(target = "rec", source = "id")
//    AccountDto toDto(Account account);
//
//    @Mapping(source = "rec", target = "id")
//    Account toEntity(AccountCreationDto dto);

}
