package com.example.asadmin.api;

import com.example.asadmin.criteria.GeneralCriteria;
import com.example.asadmin.dto.OrderDTO;
import com.example.asadmin.dto.PageResponse;
import com.example.asadmin.dto.ResponseDTO;
import com.example.asadmin.model.OrderEntity;
import com.example.asadmin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/order")
public class OrderController {

    @Autowired
    OrderService service;

    @GetMapping("")
    public PageResponse<OrderDTO> orderPageResponse(GeneralCriteria generalCriteria){
        return service.getAll(generalCriteria);
    }

    @PostMapping("")
    public ResponseEntity<ResponseDTO<OrderDTO>> create(@RequestBody OrderDTO orderDTO)
            throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ResponseDTO<OrderDTO> responseDTO = service.create(orderDTO);
        if (responseDTO.getHasErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(responseDTO);
        }
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/by-session/{sessionId}")
    public List<OrderDTO> getOrderBySessionId(@PathVariable Long sessionId){
        return service.getAllBySessionId(sessionId);
    }

    @PutMapping("/{orderId}")
    public OrderDTO updateOrder(@PathVariable Long orderId, @RequestBody OrderDTO orderDTO){
        return service.partialUpdate(orderId, orderDTO);
    }

    @GetMapping("/by-establishment/{establishmentId}")
    public List<OrderDTO> getOrderByEstablishmentId(@PathVariable Long establishmentId){
        return service.getAllByEstablishmentId(establishmentId);
    }
}
