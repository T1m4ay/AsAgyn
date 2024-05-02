package com.example.asadmin.mapper;

import com.example.asadmin.dto.OrderItemDTO;
import com.example.asadmin.model.OrderItem;
import com.example.asadmin.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
public class OrderItemMapper {
    private final OrderRepository orderRepository;

    public OrderItemMapper(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

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

        return dto;
    }

    public OrderItem toEntity(OrderItemDTO orderItemDTO){
        if(orderItemDTO == null){
            return null;
        }
        OrderItem entity = new OrderItem();
        entity.setId(orderItemDTO.getId());
        entity.setCost(orderItemDTO.getCost());
        entity.setTitle(orderItemDTO.getTitle());
        entity.setDescription(orderItemDTO.getDescription());
        entity.setQuantity(orderItemDTO.getQuantity());
        if (orderItemDTO.getOrder() != null || !ObjectUtils.isEmpty(orderItemDTO.getOrder())){
            entity.setOrder(orderRepository.getById(orderItemDTO.getOrder().getId()));
        }

        return entity;
    }

    public Set<OrderItemDTO> toDTOs(Set<OrderItem> orderItems){
        if(orderItems == null){
            return null;
        }
        Set<OrderItemDTO> orderItemDTOS = new HashSet<>();
        for(OrderItem orderItemDTO : orderItems){
            orderItemDTOS.add(toDTO(orderItemDTO));
        }
        return orderItemDTOS;
    }

    public Set<OrderItem> toEntities(Set<OrderItemDTO> orderItemDTOs){
        if(orderItemDTOs == null){
            return null;
        }
        Set<OrderItem> orderItems = new HashSet<>();
        for(OrderItemDTO orderItemDTO : orderItemDTOs){
            orderItems.add(toEntity(orderItemDTO));
        }
        return orderItems;
    }

}