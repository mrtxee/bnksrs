package org.mrtxee.bnksrs.accountservice.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import org.mrtxee.bnksrs.accountservice.dto.AccountDto;
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
    private final AccountService accountService;

    @GetMapping("/")
    public List<AccountDto> index() {
        return accountService.findAll();
    }

    @PostMapping("/")
    public AccountDto addClientAccount(@RequestBody AccountDto account) {
        return accountService.createOrGet(account);
    }

    @GetMapping("/{accountNumber}/")
    public AccountDto read(@PathVariable long accountNumber) {
        return accountService.findByAccountNumber(accountNumber);
    }

}