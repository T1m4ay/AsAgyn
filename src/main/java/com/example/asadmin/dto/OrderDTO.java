package com.example.asadmin.dto;

import com.example.asadmin.enumeration.OrderStatus;

import java.time.ZonedDateTime;

public class OrderDTO {

    private Long id;

    private OrderStatus orderStatus;

    private ZonedDateTime dateOfCreation;

    private DiningSessionDTO diningSessionDTO;

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

    public DiningSessionDTO getDiningSessionDTO() {
        return diningSessionDTO;
    }

    public void setDiningSessionDTO(DiningSessionDTO diningSessionDTO) {
        this.diningSessionDTO = diningSessionDTO;
    }
}
