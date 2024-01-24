package org.mrtxee.bnksrs;

import java.util.concurrent.Callable;
import lombok.Data;
import org.mrtxee.bnksrs.accountservice.service.AccountService;
import org.mrtxee.bnksrs.accountservice.service.TransactionRequest;
import org.mrtxee.bnksrs.accountservice.service.TransactionResponse;

@Data
public class TransferMoneyTask implements Callable<Boolean> {
    private final int id;
    private final Long payee;
    private final Long recipient;
    private final Double amount;
    private final AccountService accountService;

    public TransferMoneyTask(Long payee, Long recipient, Double amount, AccountService accountService, int id) {
        this.payee = payee;
        this.recipient = recipient;
        this.amount = amount;
        this.accountService = accountService;
        this.id = id;
    }

    @Override
    public Boolean call() {
        TransactionResponse tr = accountService.transact(new TransactionRequest(payee, recipient, amount));
        System.out.printf("task-%s in thread-%s : %s %n", this.id, Thread.currentThread().getId(), tr);
        return true;
    }

    @Override
    public String toString() {
        return "TransferMoneyTask{" + id + ":" + payee + " -> " + recipient + " (" + amount + ")" + '}';
    }
}
