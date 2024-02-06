package org.mrtxee.bnksrs.accountservice.service.impl;

import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.mrtxee.bnksrs.accountservice.repository.AccountRepository;
import org.mrtxee.bnksrs.accountservice.service.AccountNumberGenerator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountNumberGeneratorImpl implements AccountNumberGenerator {
    private final static long ACCOUNT_NUMBER_PREFIX = 4003000000000000L;
    private final static Random random = new Random();
    private final AccountRepository accountRepository;

    @Override
    public long generate() {
        long newAccountNumber;
        do {
            newAccountNumber = ACCOUNT_NUMBER_PREFIX + random.nextInt(999999999);
            //timestamp выбросить если генерация более минуты
        } while (accountRepository.existsByAccountNumber(newAccountNumber));
        return newAccountNumber;
    }
}
