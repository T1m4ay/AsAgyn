package com.example.asadmin.api.admin;

import com.example.asadmin.dto.PaymentMethodDTO;
import com.example.asadmin.model.PaymentMethod;
import com.example.asadmin.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/admin/api/payment-method")
public class AdminPaymentMethodController {

    @Autowired
    private PaymentMethodService paymentMethodService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("")
    public List<PaymentMethodDTO> findAll(){
        return paymentMethodService.findAll();
    }

}
