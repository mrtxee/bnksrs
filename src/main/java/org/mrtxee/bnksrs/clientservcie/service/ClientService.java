package org.mrtxee.bnksrs.clientservcie.service;

import java.util.List;
import org.mrtxee.bnksrs.clientservcie.model.ClientDto;

public interface ClientService {
    List<ClientDto> findAll();

    ClientDto findById(Long id);

    ClientDto create(ClientDto client);

    ClientDto update(ClientDto client);
}
