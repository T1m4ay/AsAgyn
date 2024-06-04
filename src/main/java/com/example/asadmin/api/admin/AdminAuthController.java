package com.example.asadmin.api.admin;

import com.example.asadmin.dto.JwtRequest;
import com.example.asadmin.service.AuthService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/admin/api")
public class AdminAuthController {
    private final AuthService authService;

    @PostMapping("/auth")
    @ApiOperation("Авторизация")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        return authService.createAuthToken(authRequest);
    }

}