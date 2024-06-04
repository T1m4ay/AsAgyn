package com.example.asadmin.service;

import com.example.asadmin.dto.PaymentMethodDTO;
import com.example.asadmin.mapper.PaymentMethodMapper;
import com.example.asadmin.model.PaymentMethod;
import com.example.asadmin.repository.PaymentMethodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    private final PaymentMethodMapper paymentMethodMapper;

    public List<PaymentMethodDTO> findAll(){
        return paymentMethodRepository.findAll().stream().map(paymentMethodMapper::toDTO).collect(Collectors.toList());
    }

    public PaymentMethod findById(Long id){
        return paymentMethodRepository.findById(id).orElse(null);
    }
}
