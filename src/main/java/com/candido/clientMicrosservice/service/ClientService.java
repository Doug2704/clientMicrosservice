package com.candido.clientMicrosservice.service;

import com.candido.clientMicrosservice.entity.Client;
import com.candido.clientMicrosservice.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository repository;

    public Client create(Client client) {
        return repository.save(client);
    }

    public Client findById(Long id) throws RuntimeException {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    public List<Client> findAllClients() {
        return repository.findAll();
    }

    public Client update(Long id, Client client) throws RuntimeException {
        Client c = findById(id);

        c.setName(client.getName());
        c.setNumber(client.getNumber());
        c.setCpf(client.getCpf());

        return repository.save(c);
    }

    public void delete(Long id) throws RuntimeException {
        Client c = findById(id);
        repository.delete(c);
    }

}
