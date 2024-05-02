package com.example.asadmin.mapper;

import com.example.asadmin.dto.OrderDTO;
import com.example.asadmin.model.OrderEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.time.ZonedDateTime;

@Component
@Slf4j
public class OrderMapper {

    private final DiningSessionMapper diningSessionMapper;

    private final OrderItemMapper orderItemMapper;

    public OrderMapper(
            DiningSessionMapper diningSessionMapper,
            OrderItemMapper orderItemMapper){
        this.diningSessionMapper = diningSessionMapper;
        this.orderItemMapper = orderItemMapper;
    }

    public OrderDTO toDTO(OrderEntity orderEntity){
        if (orderEntity == null){
            return null;
        }
        OrderDTO dto = new OrderDTO();
        dto.setId(orderEntity.getId());
        dto.setOrderStatus(orderEntity.getOrderStatus());
        dto.setDiningSessionDTO(diningSessionMapper.toDTO(orderEntity.getDiningSession()));
        dto.setDateOfCreation(orderEntity.getDateOfCreation().toString());
        if(orderEntity.getOrderItems() != null){
            dto.setOrderItemDTOS(orderItemMapper.toDTOs(orderEntity.getOrderItems()));
        }

        return dto;
    }

    public OrderEntity toEntity(OrderDTO orderDTO){
        OrderEntity orderEntity = new OrderEntity();
        if(!ObjectUtils.isEmpty(orderDTO.getDiningSessionDTO()) && orderDTO.getDiningSessionDTO().getId() != null){
            orderEntity.setDiningSession(diningSessionMapper.toEntity(orderDTO.getDiningSessionDTO()));
        }
        orderEntity.setDateOfCreation(ZonedDateTime.parse(orderDTO.getDateOfCreation()));
        orderEntity.setOrderStatus(orderDTO.getOrderStatus());

        return orderEntity;
    }
}