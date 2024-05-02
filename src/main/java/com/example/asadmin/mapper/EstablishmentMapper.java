package com.example.asadmin.mapper;

import com.example.asadmin.dto.EstablishmentDTO;
import com.example.asadmin.model.Establishment;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class EstablishmentMapper {

    public final PaymentMethodMapper paymentMethodMapper;

    public EstablishmentMapper(PaymentMethodMapper paymentMethodMapper) {
        this.paymentMethodMapper = paymentMethodMapper;
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
            establishment.setPaymentMethods(establishmentDTO.getPaymentMethods().stream().map(paymentMethodMapper::toEntity).collect(Collectors.toSet()));
        }

        return establishment;
    }

}