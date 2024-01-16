package org.mrtxee.bnksrs.accountservice.service;

import java.util.List;
import org.mrtxee.bnksrs.accountservice.model.AccountDto;

public interface AccountService {
    List<AccountDto> findAll();
    AccountDto findById(Long id);
    AccountDto createOrGet(AccountDto client);
    AccountDto update(AccountDto client);

}
