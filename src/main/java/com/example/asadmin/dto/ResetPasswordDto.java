package com.example.asadmin.dto;

import lombok.Data;

@Data
public class ResetPasswordDto {
    private String newPassword;
    private String confirmPassword;
    private String email;
    private String confirmCode;
}
