package com.example.asadmin.service;

import com.example.asadmin.criteria.GeneralCriteria;
import com.example.asadmin.dto.OrderDTO;
import com.example.asadmin.dto.OrderItemDTO;
import com.example.asadmin.dto.PageResponse;
import com.example.asadmin.dto.ResponseDTO;
import com.example.asadmin.enumeration.OrderStatus;
import com.example.asadmin.mapper.OrderItemMapper;
import com.example.asadmin.mapper.OrderMapper;
import com.example.asadmin.model.OrderEntity;
import com.example.asadmin.model.OrderItem;
import com.example.asadmin.repository.OrderRepository;
import com.pusher.rest.Pusher;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;

    private final OrderMapper mapper;
    private final OrderItemService orderItemService;

    private final OrderItemMapper orderItemMapper;

    public OrderEntity save(OrderEntity orderEntity){return repository.save(orderEntity);};

    public ResponseDTO<OrderDTO> create(OrderDTO orderDTO){
        ResponseDTO<OrderDTO> responseDTO = new ResponseDTO<>();

        if(ObjectUtils.isEmpty(orderDTO.getDiningSessionDTO())){
            responseDTO.setHasErrors(true);
        }
        orderDTO.setDateOfCreation(ZonedDateTime.now().toString());
        OrderEntity orderEntity = mapper.toEntity(orderDTO);
        orderEntity.setOrderStatus(OrderStatus.Processing);

        orderEntity = repository.save(orderEntity);
        Set<OrderItem> orderEntitySet = new HashSet<>();
        if (orderDTO.getOrderItemDTOS() != null || !ObjectUtils.isEmpty(orderDTO.getOrderItemDTOS())) {
            for(OrderItemDTO orderItemDTO : orderDTO.getOrderItemDTOS()) {
                orderEntitySet.add(orderItemMapper.toEntity(orderItemService.createByOrderId(orderEntity.getId(), orderItemDTO)));
            }
        }
        orderEntity.setOrderItems(orderEntitySet);
        Pusher pusher = new Pusher("1783988", "1b1aac5f3ed531c5e179", "d6eafbe9223fb0184c85");
        pusher.setCluster("ap2");
        pusher.setEncrypted(true);

        pusher.trigger(
                "AsAgyn-channel",
                "order-create-event",
                Collections.singletonMap("order", mapper.toDTO(orderEntity)));

        responseDTO.setObject(mapper.toDTO(orderEntity));

        return responseDTO;
    }

    public PageResponse<OrderDTO> getAll(GeneralCriteria generalCriteria) {
        Page<OrderEntity> orders = repository.findAll(
                PageRequest.of(
                        generalCriteria.getPage() > 0
                                ? generalCriteria.getPage() - 1
                                : 0, generalCriteria.getSize()
                )
        );

        PageResponse<OrderDTO> pageResponse = new PageResponse<>();
        pageResponse.setItems(orders.get().map(mapper::toDTO).collect(Collectors.toList()));
        pageResponse.setTotalPages(orders.getTotalPages());
        pageResponse.setTotalCount(orders.getNumber());

        return pageResponse;
    }

    public List<OrderDTO> getAllBySessionId(Long sessionId){
        List<OrderEntity> orders = repository.findAllByDiningSession_Id(sessionId);
        return orders.stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    public OrderDTO partialUpdate(Long orderId, OrderDTO orderDTO){
        if (orderDTO.getId().equals(orderId)){
            OrderEntity orderEntity = mapper.toEntity(orderDTO);
            OrderEntity dbOrderEntity = repository.findById(orderId).orElse(null);
            if(!orderEntity.getOrderStatus().equals(dbOrderEntity.getOrderStatus())){
                Pusher pusher = new Pusher("1783988", "1b1aac5f3ed531c5e179", "d6eafbe9223fb0184c85");
                pusher.setCluster("ap2");
                pusher.setEncrypted(true);

                pusher.trigger(
                        "AsAgyn-channel",
                        "order-status-update",
                        Collections.singletonMap("changed-order", orderDTO));
            }
            repository.save(orderEntity);
        }
        return orderDTO;
    }

    public List<OrderDTO> getAllByEstablishmentId(Long establishmentId){
        List<OrderEntity> orders = repository.findAllByEstablishmentId(establishmentId);
        return orders.stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    public OrderDTO findById(Long id){
        return mapper.toDTO(repository.findById(id).orElse(null));
    }
}