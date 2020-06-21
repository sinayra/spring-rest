package com.algaworks.osworks.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.algaworks.osworks.domain.exception.BusinessException;

@Entity
public class ServiceOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Client client;

    private String description;
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private ServiceOrderStatus status;

    private OffsetDateTime openedDate;
    private OffsetDateTime finishedDate;

    @OneToMany(mappedBy = "serviceOrder")
    private List<Comment> comments = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public OffsetDateTime getFinishedDate() {
        return finishedDate;
    }

    public OffsetDateTime getOpenedDate() {
        return openedDate;
    }

    public ServiceOrderStatus getStatus() {
        return status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Client getClient() {
        return client;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFinishedDate(OffsetDateTime finishedDate) {
        this.finishedDate = finishedDate;
    }

    public void setOpenedDate(OffsetDateTime openedDate) {
        this.openedDate = openedDate;
    }

    public void setStatus(ServiceOrderStatus status) {
        this.status = status;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ServiceOrder other = (ServiceOrder) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public boolean canBeFinished(){
        return getStatus().equals(ServiceOrderStatus.OPEN);
    }

	public void finish() {

        if(!canBeFinished()){
            throw new BusinessException("Service order cannot be finished");
        }

        setStatus(ServiceOrderStatus.FINISHED);
        setFinishedDate(OffsetDateTime.now());
	}

}