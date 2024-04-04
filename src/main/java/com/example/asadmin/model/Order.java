package com.example.asadmin.model;

import com.example.asadmin.enumeration.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "order")
@DynamicUpdate
@DynamicInsert
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "status")
    private OrderStatus orderStatus;

    @Column(name = "date_of_creation")
    private ZonedDateTime dateOfCreation;

    @ManyToOne(fetch = FetchType.LAZY)
    private DiningSession diningSession;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnoreProperties(value = "order")
    private Set<OrderItem> orderItems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public ZonedDateTime getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(ZonedDateTime dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public DiningSession getDiningSession() {
        return diningSession;
    }

    public void setDiningSession(DiningSession diningSession) {
        this.diningSession = diningSession;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        if(this.orderItems == null){
            this.orderItems = new HashSet<>();
        }
        this.orderItems = orderItems;
    }
}

