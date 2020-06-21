package com.algaworks.osworks.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.algaworks.osworks.api.model.ServiceOrderInputModel;
import com.algaworks.osworks.api.model.ServiceOrderModel;
import com.algaworks.osworks.domain.model.ServiceOrder;
import com.algaworks.osworks.domain.repository.ServiceOrderRepository;
import com.algaworks.osworks.domain.service.ServiceOrderService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service-order")
public class ServiceOrderController {

    @Autowired
    private ServiceOrderService serviceOrderService;

    @Autowired
    private ServiceOrderRepository serviceOrderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceOrderModel create(@Valid @RequestBody ServiceOrderInputModel serviceOrderInputModel) {
        ServiceOrder serviceOrder = toEntity(serviceOrderInputModel);

        return toModel(serviceOrderService.create(serviceOrder));
    }

    @GetMapping
    public List<ServiceOrderModel> index() {
        return toListModel(serviceOrderRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceOrderModel> show(@PathVariable Long id) {
        Optional<ServiceOrder> serviceOrder = serviceOrderRepository.findById(id);

        if (serviceOrder.isPresent()) {
            ServiceOrderModel serviceOrderModel = toModel(serviceOrder.get());
            return ResponseEntity.ok(serviceOrderModel);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/finish") 
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finish(@PathVariable Long id){
        serviceOrderService.finishServiceOrder(id);
    }

    /*
     * @PutMapping("/{id}") public ResponseEntity<ServiceOrderModel>
     * update(@PathVariable Long id, @Valid @RequestBody ServiceOrder serviceOrder)
     * {
     * 
     * if(!serviceOrderRepository.existsById(id)){ return
     * ResponseEntity.notFound().build(); }
     * 
     * serviceOrder.setId(id); serviceOrder =
     * serviceOrderService.create(serviceOrder);
     * 
     * return ResponseEntity.ok(toModel(serviceOrder)); }
     * 
     * @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long
     * id) {
     * 
     * if(!serviceOrderRepository.existsById(id)){ return
     * ResponseEntity.notFound().build(); }
     * 
     * serviceOrderService.delete(id);
     * 
     * return ResponseEntity.noContent().build(); }
     */

    private ServiceOrderModel toModel(ServiceOrder serviceOrder) {
        return modelMapper.map(serviceOrder, ServiceOrderModel.class);
    }

    private List<ServiceOrderModel> toListModel(List<ServiceOrder> serviceOrders) {
        return serviceOrders.stream().map(serviceOrder -> toModel(serviceOrder)).collect(Collectors.toList());
    }

    private ServiceOrder toEntity(ServiceOrderInputModel serviceOrderInputModel) {
        return modelMapper.map(serviceOrderInputModel, ServiceOrder.class);
    }
}