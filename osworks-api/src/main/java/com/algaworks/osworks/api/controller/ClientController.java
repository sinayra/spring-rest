package com.algaworks.osworks.api.controller;

import java.util.ArrayList;
import java.util.List;

import com.algaworks.osworks.domain.model.Client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    @GetMapping("/clients")  
    public List<Client> index(){
        List<Client> clients = new ArrayList<Client>();
        Client client1 = new Client();
        Client client2 = new Client();

        client1.setId(1L);
        client1.setName("John");
        client1.setEmail("john@localhost.com");
        client1.setPhone("123465789");

        client2.setId(2L);
        client2.setName("Doe");
        client2.setEmail("doe@localhost.com");
        client2.setPhone("987654321");

        clients.add(client1);
        clients.add(client2);

        return clients;
    }
}