package com.candido.clientMicrosservice.controller;

import com.candido.clientMicrosservice.entity.Client;
import com.candido.clientMicrosservice.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/client")
@Tag(name = "MICROSSERVIÇOS DE CLIENTES")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService service;

    @PostMapping
    @Operation(summary = "CRIAR CLIENTE", description = "ARMAZENA UM NOVO CLIENTE NO BANCO DE DADOS")
    public ResponseEntity<Client> create(@RequestBody Client client) {
        Client createdProduct = service.create(client);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    @Operation(summary = "LOCALIZAR CLIENTE", description = "MOSTRA UM CLIENTE PELO ID INFORMADO")
    public ResponseEntity<?> findById(@PathVariable Long id) throws RuntimeException {

        try {
            Client c = service.findById(id);
            return new ResponseEntity<>(c, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @Operation(summary = "VER TODOS OS CLIENTES", description = "MOSTRA TODOS OS CLIENTES ARMAZENADOS")
    public ResponseEntity<List<Client>> findAll() {
        List<Client> clients = service.findAllClients();
        return new ResponseEntity<>(clients, HttpStatus.FOUND);
    }


    @PutMapping("/{id}")
    @Operation(summary = "ALTERAR DADOS DO CLIENTE", description = "ALTERA INFORMAÇÕES DE UM CLIENTE ESPECÍFICO")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Client client) throws RuntimeException {
        try {
            Client c = service.update(id, client);
            return new ResponseEntity<>(c, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "APAGAR CLIENTE", description = "APAGA UM CLIENTE DO BANCO DE DADOS")
    public ResponseEntity<?> delete(@PathVariable Long id) throws RuntimeException {
        try {
            service.delete(id);
            return new ResponseEntity<>("Cliente deletado", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
