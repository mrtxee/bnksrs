package org.mrtxee.bnksrs.accountservice.service;

import lombok.Data;

@Data
public class TransactionRequest {
    private final long payee;
    private final long recipient;
    private final double transactionAmount;
}