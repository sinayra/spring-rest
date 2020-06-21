package com.algaworks.osworks.domain.service;

import com.algaworks.osworks.domain.repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.osworks.domain.exception.BusinessException;
import com.algaworks.osworks.domain.model.Client;

@Service
public class CreateDeleteClientService {

    @Autowired
    private ClientRepository clientRepository;
    
    public Client create(Client client){
        Client oldCliente = clientRepository.findByEmail(client.getEmail());
        if(oldCliente != null && !oldCliente.equals(client)){
            throw new BusinessException("email already registered");
        }

        return clientRepository.save(client);
    }

    public void delete(Long id){
        clientRepository.deleteById(id);
    }

}