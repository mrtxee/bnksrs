package org.mrtxee.bnksrs.accountservice.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.mrtxee.bnksrs.accountservice.model.Account;
import org.mrtxee.bnksrs.accountservice.model.AccountDto;
import org.mrtxee.bnksrs.accountservice.model.AccountDtoMapper;
import org.mrtxee.bnksrs.accountservice.repository.AccountRepository;
import org.mrtxee.bnksrs.clientservcie.repository.ClientRepository;
import org.mrtxee.bnksrs.exceptions.BadRequestException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final static long ACCOUNT_NUMBER_PREFIX = 4003000000000000L;
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;
    private final AccountDtoMapper accountDtoMapper = AccountDtoMapper.MAPPER;
    private final AccountNumberGenerator accountNumberGenerator;

    @Override
    public List<AccountDto> findAll() {
        return accountRepository.findAll().stream().map(accountDtoMapper::toDto).toList();
    }

    @Override
    public AccountDto findByAccountNumber(Long accountNumber) {
        Optional<Account> accountOptional = accountRepository.getFirstByAccountNumber(accountNumber);
        if(accountOptional.isEmpty()){
            throw new BadRequestException("accountNumber " + accountNumber + " is not found");
        }
        return accountDtoMapper.toDto(accountOptional.get());
    }

    @Override
    public AccountDto createOrGet(AccountDto dto) {
        Account account = accountDtoMapper.toEntity(dto);

        if (!clientRepository.existsById(account.getClientId())) { //если не существует хозяин счета -> исключение
            throw new BadRequestException("Client id" + account.getClientId() + "is not exists");
        }
        if (accountRepository.existsByClientId(account.getClientId())) { //если уже есть счет с таким хозяином -> вернуть счет
            return accountDtoMapper.toDto(accountRepository.getFirstByClientId(account.getClientId()));
        }
        account.setAccountNumber(accountNumberGenerator.generate()); //сгенерировать счет и создать
        return accountDtoMapper.toDto(accountRepository.save(account));
    }

    @Override
    public AccountDto update(AccountDto dto) {
        return null;
    }

}
