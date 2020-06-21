package com.algaworks.osworks.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.algaworks.osworks.domain.model.ServiceOrderStatus;

public class ServiceOrderModel {
    
    private Long id;
    private SimpleClientModel client;
    private BigDecimal price;
    private ServiceOrderStatus status;
    private OffsetDateTime openedDate;
    private OffsetDateTime finishedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SimpleClientModel getClient() {
        return client;
    }

    public void setClient(SimpleClientModel client) {
        this.client = client;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ServiceOrderStatus getStatus() {
        return status;
    }

    public void setStatus(ServiceOrderStatus status) {
        this.status = status;
    }

    public OffsetDateTime getOpenedDate() {
        return openedDate;
    }

    public void setOpenedDate(OffsetDateTime openedDate) {
        this.openedDate = openedDate;
    }

    public OffsetDateTime getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(OffsetDateTime finishedDate) {
        this.finishedDate = finishedDate;
    }
    
}