package org.mrtxee.bnksrs.accountservice.dto;

import lombok.Data;

@Data
public class TransactionRequest {
    private final long payee;
    private final long recipient;
    private final double transactionAmount;
}