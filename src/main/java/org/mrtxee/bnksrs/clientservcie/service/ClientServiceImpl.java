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
        Optional<Client> dao = repository.findById(id);
        if (dao.isPresent()) {
            return mapper.toClientDto(dao.get());
        } else {
            throw new BadRequestException("dao with id " + id + " not found");
        }
    }

    @Override
    @Transactional
    public ClientDto create(ClientDto client) {
        return mapper.toClientDto(repository.save(mapper.toClient(client)));
    }

    @Override
    public ClientDto update(ClientDto client) {
        Client dao = mapper.toClient(client);
        if (repository.existsById(dao.getId())) {
            return mapper.toClientDto(repository.save(dao));
        } else {
            throw new ResourceNotFoundException("dao with id " + dao.getId() + " not found");
        }
    }
}
