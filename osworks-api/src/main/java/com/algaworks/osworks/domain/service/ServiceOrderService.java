package com.algaworks.osworks.domain.service;

import java.time.OffsetDateTime;

import com.algaworks.osworks.domain.exception.BusinessException;
import com.algaworks.osworks.domain.exception.EntityNotFoundException;
import com.algaworks.osworks.domain.model.Client;
import com.algaworks.osworks.domain.model.Comment;
import com.algaworks.osworks.domain.model.ServiceOrder;
import com.algaworks.osworks.domain.model.ServiceOrderStatus;
import com.algaworks.osworks.domain.repository.ClientRepository;
import com.algaworks.osworks.domain.repository.CommentRepository;
import com.algaworks.osworks.domain.repository.ServiceOrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceOrderService {
    
    @Autowired
    private ServiceOrderRepository serviceOrderRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CommentRepository commentRepository;

    public ServiceOrder create(ServiceOrder serviceOrder){
        Client client = clientRepository.findById(serviceOrder.getClient().getId()).orElseThrow(() -> new BusinessException("Client not found"));
        
        serviceOrder.setClient(client);
        serviceOrder.setStatus(ServiceOrderStatus.OPEN);
        serviceOrder.setOpenedDate(OffsetDateTime.now());

        return serviceOrderRepository.save(serviceOrder);
    }

    public void delete(Long id){
        serviceOrderRepository.deleteById(id);
    }

    public Comment create(Long serviceOrderId, String description){
        ServiceOrder serviceOrder = findServiceOrder(serviceOrderId);
        Comment comment = new Comment();

        comment.setServiceOrder(serviceOrder);
        comment.setDescription(description);
        comment.setDate(OffsetDateTime.now());

        return commentRepository.save(comment);
    }

    public void finishServiceOrder(Long id){
        ServiceOrder serviceOrder = findServiceOrder(id);

        serviceOrder.finish();

        serviceOrderRepository.save(serviceOrder);
    }

    private ServiceOrder findServiceOrder(Long serviceOrderId) {
        return serviceOrderRepository.findById(serviceOrderId).orElseThrow(() -> new EntityNotFoundException("Service order not found"));
    }
    
}