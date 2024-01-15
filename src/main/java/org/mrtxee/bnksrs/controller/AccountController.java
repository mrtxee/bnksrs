package org.mrtxee.bnksrs.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    @GetMapping("/")
    public String index() {
        return "AccountController";
    }

    @PostMapping("/")
    public String addClient() {
        return "TODO: зарегистрировать счет для клиента";
    }

    @GetMapping("/{id}/")
    public String getClient(@PathVariable long id) {
        return "TODO: узнать остаток на счете "+id;
    }


    /* TODO:
        1. зарегистрировать счет для клиента `HTTP POST /account/`
            1. номер счета accountID генерируется автоматически: “4003”+<8 рандомных цифр>
        2. узнать остаток на счете `HTTP GET /account/<accountID:int>/`
    * */

}
