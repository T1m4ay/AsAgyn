package com.example.asadmin.dto;

import com.example.asadmin.enumeration.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class OrderDTO {

    private Long id;

    private OrderStatus orderStatus;

    private String dateOfCreation;

    private DiningSessionDTO diningSessionDTO = new DiningSessionDTO();

    private Set<OrderItemDTO> orderItemDTOS = new HashSet<>();

}
