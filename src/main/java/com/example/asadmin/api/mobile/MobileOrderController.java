package com.example.asadmin.api.mobile;

import com.example.asadmin.criteria.GeneralCriteria;
import com.example.asadmin.dto.OrderDTO;
import com.example.asadmin.dto.PageResponse;
import com.example.asadmin.dto.ResponseDTO;
import com.example.asadmin.dto.TableDTO;
import com.example.asadmin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
@RequestMapping(value = "/mobile/api/order")
public class MobileOrderController {

    @Autowired
    OrderService service;

    @PreAuthorize("hasAnyRole('ROLE_GUEST', 'ROLE_CUSTOMER')")
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

    @PreAuthorize("hasAnyRole('ROLE_GUEST', 'ROLE_CUSTOMER')")
    @GetMapping("/by-session/{sessionId}")
    public List<OrderDTO> getOrderBySessionId(@PathVariable Long sessionId){
        return service.getAllBySessionId(sessionId);
    }

//    @GetMapping("/by-establishment/{establishmentId}")
//    public List<OrderDTO> getOrderByEstablishmentId(@PathVariable Long establishmentId){
//        return service.getAllByEstablishmentId(establishmentId);
//    }

    @PreAuthorize("hasAnyRole('ROLE_GUEST', 'ROLE_CUSTOMER')")
    @GetMapping("/call-waiter")
    public void callWaiter(@RequestBody TableDTO tableDTO){
        service.callWaiter(tableDTO);
    }
}
