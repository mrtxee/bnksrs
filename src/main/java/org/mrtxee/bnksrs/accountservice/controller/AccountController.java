package org.mrtxee.bnksrs.accountservice.controller;

import lombok.AllArgsConstructor;
import org.mrtxee.bnksrs.accountservice.model.AccountDto;
import org.mrtxee.bnksrs.accountservice.model.useless.AccountCreationDto;
import org.mrtxee.bnksrs.accountservice.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {
    private final AccountService service;

    @GetMapping("/")
    public String index() {
        return "AccountController";
    }

    @PostMapping("/")
    public AccountDto addClientAccount(@RequestBody AccountDto account) {
        return service.createOrGet(account);
    }

    @GetMapping("/{accountNumber}/")
    public AccountDto read(@PathVariable long accountNumber) {
        return service.findByAccountNumber(accountNumber);
    }


    /* TODO:
        1. зарегистрировать счет для клиента `HTTP POST /account/`
            -. только 1 счет. найти либо создать счет.
            -. номер счета accountNumber генерируется автоматически: “4003”+<8 рандомных цифр>
        2. узнать остаток на счете `HTTP GET /account/<accountID:int>/`
    * */

}
