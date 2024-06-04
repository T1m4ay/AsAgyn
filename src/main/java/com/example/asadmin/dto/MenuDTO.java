package com.example.asadmin.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class MenuDTO {

    private Long id;

    private Set<ProductItemDTO> productItemDTOs;
}
