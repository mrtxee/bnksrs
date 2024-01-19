package org.mrtxee.bnksrs.accountservice.service;

import java.util.List;
import org.mrtxee.bnksrs.accountservice.model.AccountDto;

public interface AccountService {
    List<AccountDto> findAll();

    AccountDto findByAccountNumber(Long accountNumber);

    AccountDto createOrGet(AccountDto dto);

    TransactionResponse transact(TransactionRequest tr);

}