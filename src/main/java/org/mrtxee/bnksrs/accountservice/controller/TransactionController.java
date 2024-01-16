package org.mrtxee.bnksrs.accountservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
@AllArgsConstructor
public class TransactionController {

    @PostMapping("/")
    public String addClientAccount() {
        return "TODO: перевод между счетами";
    }

    /* TODO: перевод между счетами `HTTP POST /transaction/`
    * */

}
