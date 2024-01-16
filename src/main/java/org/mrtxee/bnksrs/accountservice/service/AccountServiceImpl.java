package org.mrtxee.bnksrs.accountservice.service;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.mrtxee.bnksrs.accountservice.model.Account;
import org.mrtxee.bnksrs.accountservice.model.AccountDto;
import org.mrtxee.bnksrs.accountservice.model.AccountMapper;
import org.mrtxee.bnksrs.accountservice.repository.AccountRepository;
import org.mrtxee.bnksrs.clientservcie.repository.ClientRepository;
import org.mrtxee.bnksrs.exceptions.BadRequestException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;
    private final AccountMapper accountMapper = AccountMapper.MAPPER;
    private final static long ACCOUNT_NUMBER_PREFIX = 4003000000000000L;

    @Override
    public List<AccountDto> findAll() {
        return null;
    }

    @Override
    public AccountDto findById(Long id) throws IllegalArgumentException {
        return null;
    }

    @Override
    public AccountDto createOrGet(AccountDto accountDto) {
        Account account = accountMapper.toEntity(accountDto);
        if(!clientRepository.existsById(account.getClientId())){ //если не существует хозяин счета -> исключение
            throw new BadRequestException("Client id"+account.getClientId()+"is not exists");
        }
        if(accountRepository.existsByClientId(account.getClientId())){ //если уже есть счет с таким хозяином -> вернуть счет
            return accountMapper.toDto(accountRepository.getFirstByClientId(account.getClientId()));
        }
        else { //сгенерировать счет и создать
            Random random = new Random();
            long newAccountNumber;
            do{
                newAccountNumber = ACCOUNT_NUMBER_PREFIX + random.nextInt(999999999);
            }
            while(accountRepository.existsByAccountNumber(newAccountNumber));
            account.setAccountNumber(newAccountNumber);
            throw new BadRequestException(account.toString());
//            return accountMapper.toDto(accountRepository.save(account));
        }
    }

    @Override
    public AccountDto update(AccountDto dto) {
        return null;
    }

}
