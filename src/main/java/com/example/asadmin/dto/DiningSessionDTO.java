package com.example.asadmin.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

public class DiningSessionDTO {

    private Long id;

    private String startDateTime;

    private EstablishmentDTO establishmentDTO = new EstablishmentDTO();

    private Boolean isClose;;

    private UserDto userDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public EstablishmentDTO getEstablishmentDTO() {
        return establishmentDTO;
    }

    public void setEstablishmentDTO(EstablishmentDTO establishmentDTO) {
        this.establishmentDTO = establishmentDTO;
    }

    public Boolean getClose() {
        return isClose;
    }

    public void setClose(Boolean close) {
        isClose = close;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
