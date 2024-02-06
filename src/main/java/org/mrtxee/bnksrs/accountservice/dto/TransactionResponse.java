package org.mrtxee.bnksrs.accountservice.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionResponse extends TransactionRequest {
    private TransactionStatus status;
    private String msg;

    public TransactionResponse(long payee, long recipient, double amount) {
        super(payee, recipient, amount);
        this.status = TransactionStatus.NEW;
        this.msg = null;
    }

    @Override
    public String toString() {
        return "TransactionResponse{" +
                super.getPayee() +" -> "+ super.getRecipient() +" ("+ super.getTransactionAmount()+") = "
                + status + " " + msg
                + '}';
    }

    public TransactionResponse(TransactionRequest tr, TransactionStatus status, String msg) {
        super(tr.getPayee(), tr.getRecipient(), tr.getTransactionAmount());
        this.status = status;
        this.msg = msg;
    }
}
