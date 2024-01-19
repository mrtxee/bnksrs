package org.mrtxee.bnksrs.clientservcie.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.mrtxee.bnksrs.clientservcie.model.ClientDto;
import org.mrtxee.bnksrs.clientservcie.service.ClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService service;

    @GetMapping("/")
    public List<ClientDto> getAllClients() {
        return service.findAll();
    }

    @GetMapping("/{id}/")
    public ClientDto getClient(@PathVariable long id) {
        return service.findById(id);
    }

    @PutMapping("/")
    public ClientDto updateClient(@RequestBody ClientDto client) {
        return service.update(client);
    }

    @PostMapping("/")
    public ClientDto addClient(@RequestBody ClientDto client) {
        return service.create(client);
    }

}
