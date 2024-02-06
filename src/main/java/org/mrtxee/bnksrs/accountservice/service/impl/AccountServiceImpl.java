package org.mrtxee.bnksrs.accountservice.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.mrtxee.bnksrs.accountservice.dto.TransactionRequest;
import org.mrtxee.bnksrs.accountservice.dto.TransactionResponse;
import org.mrtxee.bnksrs.accountservice.dto.TransactionStatus;
import org.mrtxee.bnksrs.accountservice.model.Account;
import org.mrtxee.bnksrs.accountservice.dto.AccountDto;
import org.mrtxee.bnksrs.accountservice.dto.mapper.AccountDtoMapper;
import org.mrtxee.bnksrs.accountservice.repository.AccountRepository;
import org.mrtxee.bnksrs.accountservice.service.AccountNumberGenerator;
import org.mrtxee.bnksrs.accountservice.service.AccountService;
import org.mrtxee.bnksrs.clientservcie.repository.ClientRepository;
import org.mrtxee.bnksrs.exceptions.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final static long MIN_TRANSFER_AMOUNT = 1;
    private final static long MAX_TRANSFER_AMOUNT = 999999999;
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;
    private final AccountDtoMapper accountDtoMapper = AccountDtoMapper.MAPPER;
    private final AccountNumberGenerator accountNumberGenerator;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public List<AccountDto> findAll() {
        return accountRepository.findAll().stream().map(accountDtoMapper::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public AccountDto findByAccountNumber(Long accountNumber) {
        Optional<Account> accountOptional = accountRepository.getFirstByAccountNumber(accountNumber);
        if (accountOptional.isEmpty()) {
            throw new BadRequestException("accountNumber " + accountNumber + " is not found");
        }
        return accountDtoMapper.toDto(accountOptional.get());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public AccountDto createOrGet(AccountDto dto) {
        Account account = accountDtoMapper.toEntity(dto);

        if (!clientRepository.existsById(account.getClientId())) { //если не существует хозяин счета -> исключение
            throw new BadRequestException("Client id " + account.getClientId() + " is not exists");
        }
        if (accountRepository.existsByClientId(account.getClientId())) { //если уже есть счет с таким хозяином -> вернуть счет
            return accountDtoMapper.toDto(accountRepository.getFirstByClientId(account.getClientId()));
        }
        account.setAccountNumber(accountNumberGenerator.generate()); //сгенерировать счет и создать
        return accountDtoMapper.toDto(accountRepository.save(account));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ)
    public TransactionResponse transact(TransactionRequest tr) {
        /*
        * TransactionStatus.FAIL не кетчится т.к. в этом методе нет транзакций изза того, что AccountServiceImpl
        * представляет собой прокси.
        * Решение в селфИнджекции, либо @Ретрай, либо добавление доп слоя, который будет вызывать transact(); -- разруливание коллизии путем пессимистичной блокировки
        * т.к. вероятность коллизии маленькая -> стоит использовать оптимистичную блокировку, которая заключаетс в том, что
        * делаем аналогично CAS при помощи Hibernate @Version
        * */

        if (tr.getTransactionAmount() < MIN_TRANSFER_AMOUNT || tr.getTransactionAmount() > MAX_TRANSFER_AMOUNT) {
            throw new BadRequestException(String.format("transfer amount should be between %s and %s", MIN_TRANSFER_AMOUNT, MAX_TRANSFER_AMOUNT));
        }
        List<Account> agents = accountRepository.findByAccountNumberIn(Arrays.asList(tr.getPayee(), tr.getRecipient()));
        if (agents.size() != 2) {
            throw new BadRequestException("insufficient account number provided");
        }
        Account payee = agents.get(0).getAccountNumber() == tr.getPayee() ? agents.get(0) : agents.get(1);
        Account recipient = agents.get(0).getAccountNumber() == tr.getPayee() ? agents.get(1) : agents.get(0);
        if (payee.getAmount() < tr.getTransactionAmount()) {
            return new TransactionResponse(tr, TransactionStatus.PAYEE_INSUFFICIENT_FUNDS, "payee insufficient funds. " + payee.getAccountNumber() + " has only " + payee.getAmount());
        }
        payee.setAmount(payee.getAmount() - tr.getTransactionAmount());
        recipient.setAmount(recipient.getAmount() + tr.getTransactionAmount());
        try {
            accountRepository.save(payee);
            accountRepository.save(recipient);
            return new TransactionResponse(tr, TransactionStatus.SUCCESS, "");
        } catch (Exception e) {
            return new TransactionResponse(tr, TransactionStatus.FAIL, e.toString());
        }

    }

}
