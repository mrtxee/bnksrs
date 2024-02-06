package org.mrtxee.bnksrs.accountservice.controller;

import lombok.AllArgsConstructor;
import org.mrtxee.bnksrs.accountservice.service.AccountService;
import org.mrtxee.bnksrs.accountservice.dto.TransactionRequest;
import org.mrtxee.bnksrs.accountservice.dto.TransactionResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
@AllArgsConstructor
public class TransactionController {
    private final AccountService accountService;

    @PostMapping("/")
    public TransactionResponse transact(@RequestBody TransactionRequest TransactionRequest) {
        return accountService.transact(TransactionRequest);
    }

    /* TODO: перевод между счетами `HTTP POST /transaction/`
     * */

}
