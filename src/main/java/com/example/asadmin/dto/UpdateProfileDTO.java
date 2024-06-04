package com.example.asadmin.dto;

import lombok.Data;

@Data
public class UpdateProfileDTO {
    private String username;
    private String newPassword;
    private String confirmPassword;
    private String email;
}
