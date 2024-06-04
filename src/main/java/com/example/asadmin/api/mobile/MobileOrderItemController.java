package com.example.asadmin.api.mobile;

import com.example.asadmin.dto.OrderItemDTO;
import com.example.asadmin.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/mobile/api/order-item")
public class MobileOrderItemController {

    @Autowired
    OrderItemService orderItemService;

    @PreAuthorize("hasAnyRole('ROLE_GUEST', 'ROLE_CUSTOMER')")
    @PostMapping("/{orderId}")
    public OrderItemDTO createOrderItem(@PathVariable Long orderId, @RequestBody OrderItemDTO orderItemDTO){
        return orderItemService.createByOrderId(orderId, orderItemDTO);
    }
}
