package com.example.asadmin.service;

import com.example.asadmin.dto.OrderDTO;
import com.example.asadmin.dto.OrderItemDTO;
import com.example.asadmin.mapper.OrderItemMapper;
import com.example.asadmin.mapper.OrderMapper;
import com.example.asadmin.model.OrderItem;
import com.example.asadmin.repository.OrderItemRepository;
import com.example.asadmin.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository repository;

    private final OrderRepository orderRepository;

    private final OrderItemMapper mapper;

    private final OrderMapper orderMapper;

    public OrderItemDTO createByOrderId(Long orderId, OrderItemDTO orderItemDTO){
        orderItemDTO.setOrder(orderMapper.toDTO(orderRepository.findById(orderId).orElse(null)));
        return mapper.toDTO(repository.save(mapper.toEntity(orderItemDTO)));
    }
}
