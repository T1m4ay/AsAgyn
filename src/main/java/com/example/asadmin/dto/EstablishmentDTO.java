package com.example.asadmin.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Setter
@Getter
public class EstablishmentDTO {

    private Long id;

    private String establishmentName;

    private BigDecimal servingPercentage;

    private String backgroundImage;

    private Set<PaymentMethodDTO> paymentMethods;

}
