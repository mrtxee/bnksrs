package org.mrtxee.bnksrs.lto;

import java.util.concurrent.Callable;
import lombok.Data;
import org.mrtxee.bnksrs.accountservice.service.AccountService;
import org.mrtxee.bnksrs.accountservice.service.TransactionRequest;
import org.mrtxee.bnksrs.accountservice.service.TransactionResponse;

@Data
//@AllArgsConstructor
public class TransferMoneyTask implements Callable<Boolean> {
    private Long payee;
    private Long recipient;
    private Double amount;
    private AccountService accountService;

    public TransferMoneyTask(Long payee, Long recipient, Double amount, AccountService accountService) {
        this.payee = payee;
        this.recipient = recipient;
        this.amount = amount;
        this.accountService = accountService;
    }

    @Override
    public Boolean call() {
        TransactionResponse tr = accountService.transact(new TransactionRequest(payee, recipient, amount));
        System.out.printf("doing %s with %s%n", tr, Thread.currentThread().getId());
        return true;
    }
}
