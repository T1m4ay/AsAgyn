package com.example.asadmin.api.mobile;

import com.example.asadmin.dto.*;
import com.example.asadmin.service.AuthService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/mobile/api")
public class MobileAuthController {
    private final AuthService authService;

    @PostMapping("/auth")
    @ApiOperation("Auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        return authService.createAuthToken(authRequest);
    }

    @PostMapping("/send-confirm-code")
    @ApiOperation("Send confirm code")
    public ResponseEntity<?> sendConfirmCode(@RequestBody SendCodeRequest sendCodeRequest) {
        return authService.sendConfirmCode(sendCodeRequest);
    }

    @PostMapping("/resending-confirm-code")
    @ApiOperation("Resending registration code")
    public ResponseEntity<?> resendingCode(@RequestBody SendCodeRequest sendCodeRequest) {
        return authService.resendingCode(sendCodeRequest);
    }

    @PostMapping("/registration")
    @ApiOperation("Confirm registration email")
    public ResponseEntity<?> confirmEmail(@RequestBody ConfirmEmailDTO confirmEmailDTO){
        return authService.registration(confirmEmailDTO);
    }

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER')")
    @PostMapping("/update-profile")
    @ApiOperation("Update Profile")
    public ResponseEntity<?> updateProfile(@RequestBody UpdateProfileDTO updateProfileDTO) {
        return authService.updateProfileInfo(updateProfileDTO);
    }

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER')")
    @GetMapping("/get-profile")
    @ApiOperation("Get Profile")
    public ResponseEntity<?> getProfile() {
        return authService.getProfileInfo();
    }

    @PostMapping("/send-password-reset-code")
    @ApiOperation("Send reset password code")
    public ResponseEntity<?> sendPasswordResetCode(@RequestBody SendCodeRequest sendCodeRequest) {
        return authService.sendPasswordResetCode(sendCodeRequest);
    }

    @PostMapping("/resending-password-reset-code")
    @ApiOperation("Resending registration code")
    public ResponseEntity<?> resendingPasswordResetCode(@RequestBody SendCodeRequest sendCodeRequest) {
        return authService.resendingPasswordResetCode(sendCodeRequest);
    }

    @PostMapping("/reset-password")
    @ApiOperation("Confirm registration email")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto){
        return authService.resetPassword(resetPasswordDto);
    }
}