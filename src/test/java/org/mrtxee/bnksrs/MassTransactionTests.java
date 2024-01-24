package org.mrtxee.bnksrs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import org.junit.jupiter.api.Test;
import org.mrtxee.bnksrs.accountservice.model.AccountDto;
import org.mrtxee.bnksrs.accountservice.repository.AccountRepository;
import org.mrtxee.bnksrs.accountservice.service.AccountService;
import org.mrtxee.bnksrs.clientservcie.model.ClientDto;
import org.mrtxee.bnksrs.clientservcie.repository.ClientRepository;
import org.mrtxee.bnksrs.clientservcie.service.ClientService;
import org.mrtxee.bnksrs.lto.TransferMoneyTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import com.github.javafaker.Faker;

@SpringBootTest
class MassTransactionTests {
    @Autowired
    private AccountService accountService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ClientRepository clientRepository;

    private double getAccountsSum(List<AccountDto> accounts) {
        double result = 0;
        for (AccountDto accTransient : accounts) {
            AccountDto accDetached = accountService.findByAccountNumber(accTransient.getAccountNumber());
            result += accDetached.getAmount();
        }
        return result;
    }

    @Test
    void massTransactionsDefTest() {
        commonTest(5, 1000, 50, 2, 9, 3);
    }

    @Test
    void concurrentReadTest() {
        commonTest(2, 1000D, 16, 1, 1, 8);
    }

    void commonTest(int ACCOUNTS_COUNT, double ACCOUNTS_START_BALANCE, int TRANSACTIONS_COUNT, int TRANSACTION_MIN_SUM, int TRANSACTION_MAX_SUM, int THREADS_COUNT) {
        final Random random = new Random();
        final double sum1, sum2;
        //+зарегистрировать 3 пользователя
        //+зарегистрировать каждому счет
        List<AccountDto> accounts = new ArrayList<>();
        for (int i = 0; i < ACCOUNTS_COUNT; i++) {
            ClientDto client = clientService.create(new ClientDto(0L, RandomNameGetter.getRandomName(), RandomNameGetter.getRandomName(), RandomNameGetter.getRandomName()));
            accounts.add(accountService.createOrGet(new AccountDto(client.getRec(), ACCOUNTS_START_BALANCE)));
        }
        //+сгенерировать 100 транзакций между сетами
        List<TransferMoneyTask> trTasks = new LinkedList<>();
        for (int i = 0; i < TRANSACTIONS_COUNT; i++) {
            long recipient, payee = accounts.get(random.nextInt(0, accounts.size())).getAccountNumber();
            do {
                recipient = accounts.get(random.nextInt(0, accounts.size())).getAccountNumber();
            } while (payee == recipient);
            double transactionSum = random.nextInt(TRANSACTION_MIN_SUM, TRANSACTION_MAX_SUM + 1);
            trTasks.add(new TransferMoneyTask(payee, recipient, transactionSum, accountService, i));
        }
        sum1 = getAccountsSum(accounts);
        //+выполнить транзакции
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(THREADS_COUNT);
        try {
            executor.invokeAll(trTasks);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //+сравнить сумму всех транзакций до и после
        sum2 = getAccountsSum(accounts);
        assertThat(sum1 - sum2).isEqualTo(0);
        //+очистить базу
        accountRepository.deleteAll();
        clientRepository.deleteAll();
    }

}
