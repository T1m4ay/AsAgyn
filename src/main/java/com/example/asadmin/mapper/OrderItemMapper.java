package com.example.asadmin.mapper;

import com.example.asadmin.dto.OrderItemDTO;
import com.example.asadmin.model.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

    OrderMapper orderMapper;

    public OrderItemDTO toDTO(OrderItem orderItem){
        if(orderItem == null){
            return null;
        }
        OrderItemDTO dto = new OrderItemDTO();
        dto.setId(orderItem.getId());
        dto.setCost(orderItem.getCost());
        dto.setTitle(orderItem.getTitle());
        dto.setDescription(orderItem.getDescription());
        dto.setQuantity(orderItem.getQuantity());
        dto.setOrder(orderMapper.toDTO(orderItem.getOrder()));

        return dto;
    }

}