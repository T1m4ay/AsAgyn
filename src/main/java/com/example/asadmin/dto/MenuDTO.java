package com.example.asadmin.dto;

public class MenuDTO {

    private Long id;

    private EstablishmentDTO establishmentDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EstablishmentDTO getEstablishmentDTO() {
        return establishmentDTO;
    }

    public void setEstablishmentDTO(EstablishmentDTO establishmentDTO) {
        this.establishmentDTO = establishmentDTO;
    }
}
