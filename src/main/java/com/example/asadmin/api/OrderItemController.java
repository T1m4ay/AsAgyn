package com.example.asadmin.api;

import com.example.asadmin.dto.OrderDTO;
import com.example.asadmin.dto.OrderItemDTO;
import com.example.asadmin.model.OrderItem;
import com.example.asadmin.repository.OrderItemRepository;
import com.example.asadmin.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/order-item")
public class OrderItemController {

    @Autowired
    OrderItemService orderItemService;

//    @Autowired
//    OrderItemRepository orderItemRepository;
//
//
//    @GetMapping("/order-by/{orderId}")
//    public OrderItem getOrderById(@PathVariable Long orderId){
//        return orderItemRepository.findById(orderId).orElse(null);
//    }

    @PostMapping("/{orderId}")
    public OrderItemDTO createOrderItem(@PathVariable Long orderId, @RequestBody OrderItemDTO orderItemDTO){
        return orderItemService.createByOrderId(orderId, orderItemDTO);
    }
}
