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
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository repository;
    private final ClientMapper mapper = ClientMapper.MAPPER;

    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ClientDto> findAll() {
        return repository.findAll().stream().map(mapper::toClientDto).toList();
    }

    @Override
    public ClientDto findById(Long id) {
        Optional<Client> clientOptional = repository.findById(id);
        if(clientOptional.isEmpty()){
            throw new BadRequestException("Client with id " + id + " is not found");
        }
        return mapper.toClientDto(clientOptional.get());
    }

    @Override
    @Transactional
    public ClientDto create(ClientDto client) {
        return mapper.toClientDto(repository.save(mapper.toClient(client)));
    }

    @Override
    public ClientDto update(ClientDto clientDto) {
        Client client = mapper.toClient(clientDto);
        if (repository.existsById(client.getId())) {
            return mapper.toClientDto(repository.save(client));
        } else {
            throw new ResourceNotFoundException("dao with id " + client.getId() + " not found");
        }
    }
}
