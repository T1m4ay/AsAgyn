package com.example.asadmin.api;

import com.example.asadmin.criteria.GeneralCriteria;
import com.example.asadmin.dto.OrderDTO;
import com.example.asadmin.dto.PageResponse;
import com.example.asadmin.dto.ResponseDTO;
import com.example.asadmin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;

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

}
