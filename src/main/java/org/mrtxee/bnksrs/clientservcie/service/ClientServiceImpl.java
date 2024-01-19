package org.mrtxee.bnksrs.clientservcie.service;

import java.util.List;
import java.util.Optional;
import org.mrtxee.bnksrs.clientservcie.model.Client;
import org.mrtxee.bnksrs.clientservcie.model.ClientDto;
import org.mrtxee.bnksrs.clientservcie.model.ClientMapper;
import org.mrtxee.bnksrs.clientservcie.repository.ClientRepository;
import org.mrtxee.bnksrs.exceptions.BadRequestException;
import org.mrtxee.bnksrs.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service

public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper = ClientMapper.MAPPER;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public List<ClientDto> findAll() {
        return clientRepository.findAll().stream().map(clientMapper::toClientDto).toList();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public ClientDto findById(Long id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isEmpty()) {
            throw new BadRequestException("Client with id " + id + " is not found");
        }
        return clientMapper.toClientDto(clientOptional.get());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public ClientDto create(ClientDto clientDto) {
        return clientMapper.toClientDto(clientRepository.save(clientMapper.toClient(clientDto)));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public ClientDto update(ClientDto clientDto) {
        Client client = clientMapper.toClient(clientDto);
        if (clientRepository.existsById(client.getId())) {
            return clientMapper.toClientDto(clientRepository.save(client));
        } else {
            throw new ResourceNotFoundException("Client with id " + client.getId() + " is not found");
        }
    }
}
