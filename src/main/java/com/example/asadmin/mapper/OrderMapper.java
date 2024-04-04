package com.example.asadmin.mapper;

import com.example.asadmin.dto.OrderDTO;
import com.example.asadmin.model.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class OrderMapper {

    private final DiningSessionMapper diningSessionMapper;

    public OrderMapper(
            DiningSessionMapper diningSessionMapper
    ){
        this.diningSessionMapper = diningSessionMapper;
    }

    public OrderDTO toDTO(Order order){
        if (order == null){
            return null;
        }
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setOrderStatus(order.getOrderStatus());
        dto.setDiningSessionDTO(diningSessionMapper.toDTO(order.getDiningSession()));
        dto.setDateOfCreation(order.getDateOfCreation());

        return dto;
    }

    public Order toEntity(OrderDTO orderDTO){
        Order order = new Order();

        if(!ObjectUtils.isEmpty(orderDTO.getDiningSessionDTO())){
            order.setDiningSession(diningSessionMapper.toEntity(orderDTO.getDiningSessionDTO()));
        }

        return order;
    }
}