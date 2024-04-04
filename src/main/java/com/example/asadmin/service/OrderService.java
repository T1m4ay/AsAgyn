package com.example.asadmin.service;

import com.example.asadmin.criteria.GeneralCriteria;
import com.example.asadmin.dto.OrderDTO;
import com.example.asadmin.dto.PageResponse;
import com.example.asadmin.dto.ResponseDTO;
import com.example.asadmin.enumeration.OrderStatus;
import com.example.asadmin.mapper.OrderMapper;
import com.example.asadmin.model.Order;
import com.example.asadmin.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.ZonedDateTime;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private OrderRepository repository;

    private OrderMapper orderMapper;

    public Order save(Order order){return repository.save(order);};

    public ResponseDTO<OrderDTO> create(OrderDTO orderDTO){
        ResponseDTO<OrderDTO> responseDTO = new ResponseDTO<>();

        if(ObjectUtils.isEmpty(orderDTO.getDiningSessionDTO())){
            responseDTO.setHasErrors(true);
        }

        Order order = orderMapper.toEntity(orderDTO);
        order.setDateOfCreation(ZonedDateTime.now());
        order.setOrderStatus(OrderStatus.Processing);

        repository.save(order);

        responseDTO.setObject(orderMapper.toDTO(order));

        return responseDTO;
    }

    public PageResponse<OrderDTO> getAll(GeneralCriteria generalCriteria) {
        Page<Order> orders = repository.findAll(
                PageRequest.of(
                        generalCriteria.getPage() > 0
                                ? generalCriteria.getPage() - 1
                                : 0, generalCriteria.getSize()
                )
        );

        PageResponse<OrderDTO> pageResponse = new PageResponse<>();
        pageResponse.setItems(orders.get().map(orderMapper::toDTO).collect(Collectors.toList()));
        pageResponse.setTotalPages(orders.getTotalPages());
        pageResponse.setTotalCount(orders.getNumber());

        return pageResponse;
    }

}