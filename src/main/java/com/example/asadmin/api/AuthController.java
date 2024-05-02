package com.example.asadmin.api;

import com.example.asadmin.dto.JwtRequest;
import com.example.asadmin.dto.RegistrationUserDto;
import com.example.asadmin.dto.ResetPasswordDto;
import com.example.asadmin.service.AuthService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/auth")
    @ApiOperation("Auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        return authService.createAuthToken(authRequest);
    }

    @PostMapping("/registration")
    @ApiOperation("Registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
        return authService.createNewUser(registrationUserDto);
    }

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER')")
    @PostMapping("/update-profile")
    @ApiOperation("Update Profile")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {
        return authService.updateProfileInfo(resetPasswordDto);
    }

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER')")
    @GetMapping("/get-profile")
    @ApiOperation("Get Profile")
    public ResponseEntity<?> getProfile() {
        return authService.getProfileInfo();
    }
}