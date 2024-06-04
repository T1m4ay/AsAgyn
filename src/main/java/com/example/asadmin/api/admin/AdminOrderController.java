package com.example.asadmin.api.admin;

import com.example.asadmin.criteria.GeneralCriteria;
import com.example.asadmin.dto.OrderDTO;
import com.example.asadmin.dto.PageResponse;
import com.example.asadmin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admin/api/order")
public class AdminOrderController {

    @Autowired
    OrderService service;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("")
    public PageResponse<OrderDTO> orderPageResponse(GeneralCriteria generalCriteria){
        return service.getAll(generalCriteria);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping("/{orderId}")
    public OrderDTO updateOrder(@PathVariable Long orderId, @RequestBody OrderDTO orderDTO){
        return service.partialUpdate(orderId, orderDTO);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/by-session/{sessionId}")
    public List<OrderDTO> getOrderBySessionId(@PathVariable Long sessionId){
        return service.getAllBySessionId(sessionId);
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/by-establishment/{establishmentId}")
    public PageResponse<OrderDTO> getOrderByEstablishmentId(@PathVariable Long establishmentId, GeneralCriteria generalCriteria){
        return service.getAllByEstablishmentId(establishmentId, generalCriteria);
    }
}
