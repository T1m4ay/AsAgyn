package com.example.asadmin.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderItemDTO {

    private Long id;

    private String title;

    private String description;

    private int quantity;

    private int cost;

    private OrderDTO order;
}