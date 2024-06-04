package com.example.asadmin.mapper;

import com.example.asadmin.dto.EstablishmentDTO;
import com.example.asadmin.dto.PaymentMethodDTO;
import com.example.asadmin.model.Establishment;
import com.example.asadmin.model.PaymentMethod;
import com.example.asadmin.service.PaymentMethodService;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EstablishmentMapper {

    public final PaymentMethodMapper paymentMethodMapper;

    public final PaymentMethodService paymentMethodService;

    public EstablishmentMapper(PaymentMethodMapper paymentMethodMapper, PaymentMethodService paymentMethodService) {
        this.paymentMethodMapper = paymentMethodMapper;
        this.paymentMethodService = paymentMethodService;
    }

    public EstablishmentDTO toDTO(Establishment establishment){
        if(establishment == null){
            return null;
        }
        EstablishmentDTO dto = new EstablishmentDTO();
        dto.setId(establishment.getId());
        dto.setEstablishmentName(establishment.getName());
        dto.setBackgroundImage(establishment.getBackgroundImage());
        dto.setServingPercentage(establishment.getServingPercentage());
        if(establishment.getPaymentMethods() != null){
            dto.setPaymentMethods(establishment.getPaymentMethods().stream().map(paymentMethodMapper::toDTO).collect(Collectors.toSet()));
        }

        return dto;
    }

    public Establishment toEntity(EstablishmentDTO establishmentDTO){
        if(establishmentDTO == null){
            return null;
        }

        Establishment establishment = new Establishment();
        establishment.setId(establishmentDTO.getId());
        establishment.setBackgroundImage(establishmentDTO.getBackgroundImage());
        establishment.setName(establishmentDTO.getEstablishmentName());
        establishment.setServingPercentage(establishmentDTO.getServingPercentage());
        if(establishmentDTO.getPaymentMethods() != null){
            Set<PaymentMethod> paymentMethods = new HashSet<>();
            for (PaymentMethodDTO dto : establishmentDTO.getPaymentMethods()){
                paymentMethods.add(paymentMethodService.findById(dto.getId()));
            }
            establishment.setPaymentMethods(paymentMethods);
        }

        return establishment;
    }

}