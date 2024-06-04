package com.example.asadmin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfirmEmailDTO {

    public RegistrationUserDto registrationUserDto;
    public String code;
}
