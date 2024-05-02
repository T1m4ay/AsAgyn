package com.example.asadmin.mapper;

import com.example.asadmin.dto.PaymentMethodDTO;
import com.example.asadmin.model.PaymentMethod;
import org.springframework.stereotype.Component;

@Component
public class PaymentMethodMapper {

    public PaymentMethodDTO toDTO(PaymentMethod entity){
        if(entity == null){
            return null;
        }

        PaymentMethodDTO dto = new PaymentMethodDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());

        return dto;
    }

    public PaymentMethod toEntity(PaymentMethodDTO dto){
        if(dto == null){
            return null;
        }

        PaymentMethod entity = new PaymentMethod();
        entity.setId(dto.getId());
        entity.setName(dto.getName());

        return entity;
    }
}
