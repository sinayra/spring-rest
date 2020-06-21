package com.algaworks.osworks.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.algaworks.osworks.api.model.ClientInputModel;
import com.algaworks.osworks.api.model.ClientModel;
import com.algaworks.osworks.domain.model.Client;
import com.algaworks.osworks.domain.repository.ClientRepository;
import com.algaworks.osworks.domain.service.CreateDeleteClientService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CreateDeleteClientService clienteService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<ClientModel> index() {
        return toListModel(clientRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientModel> show(@PathVariable Long id) {
        Optional<Client> client = clientRepository.findById(id);

        if (client.isPresent()) {
            return ResponseEntity.ok(toModel(client.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientModel create(@Valid @RequestBody ClientInputModel clientInputModel) {
        Client client = toEntity(clientInputModel);

        return toModel(clienteService.create(client));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientModel> update(@PathVariable Long id, @Valid @RequestBody Client client) {

        if (!clientRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        client.setId(id);
        client = clienteService.create(client);

        return ResponseEntity.ok(toModel(client));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        if (!clientRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        clienteService.delete(id);

        return ResponseEntity.noContent().build();
    }

    private ClientModel toModel(Client client) {
        return modelMapper.map(client, ClientModel.class);
    }

    private List<ClientModel> toListModel(List<Client> clients) {
        return clients.stream().map(client -> toModel(client)).collect(Collectors.toList());
    }

    private Client toEntity(ClientInputModel clientInputModel) {
        return modelMapper.map(clientInputModel, Client.class);
    }
}