package com.example.asadmin.dto;

import java.time.ZonedDateTime;

public class DiningSessionDTO {

    private Long id;

    private ZonedDateTime startDateTime;

    private EstablishmentDTO establishmentDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(ZonedDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public EstablishmentDTO getEstablishmentDTO() {
        return establishmentDTO;
    }

    public void setEstablishmentDTO(EstablishmentDTO establishmentDTO) {
        this.establishmentDTO = establishmentDTO;
    }
}
