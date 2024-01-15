package org.mrtxee.bnksrs.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
@AllArgsConstructor
public class ClientController {

    @GetMapping("/")
    public String index() {
        return "ClientController";
    }

    @PostMapping("/")
    public String addClient() {
        return "TODO: зарегистрировать клиента";
    }

    @GetMapping("/{id}/")
    public String getClient(@PathVariable long id) {
        return "TODO: получить данные о клиенте для "+id;
    }


    /* TODO:
        1. зарегистрировать клиента `HTTP POST /client/`
        2. получить данные о клиенте `HTTP GET /client/<ID:int>/`
            - вернуть пользователя и список его счетов
    * */

}
