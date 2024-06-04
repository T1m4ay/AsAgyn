package com.example.asadmin.service;

import com.example.asadmin.dto.*;
import com.example.asadmin.enumeration.CodeType;
import com.example.asadmin.model.ConfirmCodes;
import com.example.asadmin.model.User;
import com.example.asadmin.utils.JwtTokenUtils;
import com.example.asadmin.web.rest.confirm.AppMessage;
import com.example.asadmin.web.rest.errors.AppError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;
    private final ConfirmCodesService confirmCodesService;

    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Неправильный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    public ResponseEntity<?> sendConfirmCode(@RequestBody SendCodeRequest sendCodeRequest) {
        AppMessage message = confirmCodesService.createConfirmCode(sendCodeRequest);
        return ResponseEntity.ok(message);
    }

    public ResponseEntity<?> resendingCode(@RequestBody SendCodeRequest sendCodeRequest){
        AppMessage message = confirmCodesService.resendingConfirmCode(sendCodeRequest);
        return ResponseEntity.ok(message);
    }

    public ResponseEntity<?> updateProfileInfo(@RequestBody UpdateProfileDTO updateProfileDTO) {
        if(updateProfileDTO.getNewPassword() != null){
            if (!updateProfileDTO.getNewPassword().equals(updateProfileDTO.getConfirmPassword())) {
                return new ResponseEntity<>(
                        new AppError(HttpStatus.BAD_REQUEST.value(),
                                "Пароли не совпадают"), HttpStatus.BAD_REQUEST);
            }

            Optional<User> optionalUser = userService.findByUsername(updateProfileDTO.getUsername());
            if (optionalUser.isEmpty()) {
                return new ResponseEntity<>(
                        new AppError(HttpStatus.NOT_FOUND.value(),
                                "Пользователь не найден"), HttpStatus.NOT_FOUND);
            }

            userService.changePassword(optionalUser, updateProfileDTO.getNewPassword());
        }

        if(updateProfileDTO.getEmail() != null){

            Optional<User> optionalUser = userService.findByUsername(updateProfileDTO.getUsername());
            if (optionalUser.isEmpty()) {
                return new ResponseEntity<>(
                        new AppError(HttpStatus.NOT_FOUND.value(),
                                "Пользователь не найден"), HttpStatus.NOT_FOUND);
            }

            userService.changeEmail(optionalUser ,updateProfileDTO);
        }
        return ResponseEntity.ok(new AppMessage("Изменение профиля прошла успешно"));
    }

    public ResponseEntity<?> getProfileInfo() {
        User user = userService.getCurrentUser();
        return ResponseEntity.ok(new UserDto(user.getId(), user.getUsername(), user.getEmail()));
    }

    public ResponseEntity<?> registration(ConfirmEmailDTO confirmEmailDTO){
        ResponseDTO<ConfirmCodes> responseDTO =
                confirmCodesService.checkCode(
                        confirmEmailDTO.getCode(),
                        confirmEmailDTO.getRegistrationUserDto().getEmail(),
                        CodeType.register);
        if(responseDTO.getHasErrors()){
            return ResponseEntity.badRequest().body(responseDTO);
        }
        confirmCodesService.removeConfirmCode(confirmEmailDTO.getRegistrationUserDto());
        return finalRegistration(confirmEmailDTO.getRegistrationUserDto());
    }

    public ResponseEntity<?> finalRegistration(RegistrationUserDto registrationUserDto){
        if (!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.BAD_REQUEST.value(),
                            "Пароли не совпадают"), HttpStatus.BAD_REQUEST);
        }
        if (userService.findByUsername(registrationUserDto.getUsername()).isPresent()) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.BAD_REQUEST.value(),
                            "Пользователь с указанным именем уже существует"), HttpStatus.BAD_REQUEST);
        }
        User user = userService.createNewUser(registrationUserDto);
        return ResponseEntity.ok(new UserDto(user.getId(), user.getUsername(), user.getEmail()));
    }

    public ResponseEntity<?> sendPasswordResetCode(@RequestBody SendCodeRequest sendCodeRequest) {
        AppMessage message = confirmCodesService.createPasswordResetCode(sendCodeRequest);
        return ResponseEntity.ok(message);
    }

    public ResponseEntity<?> resetPassword(ResetPasswordDto resetPasswordDto){
        ResponseDTO<ConfirmCodes> responseDTO =
                confirmCodesService.checkCode(
                        resetPasswordDto.getConfirmCode(),
                        resetPasswordDto.getEmail(),
                        CodeType.resetPassword);
        if (responseDTO.getHasErrors()){
            return ResponseEntity.badRequest().body(responseDTO);
        }
        confirmCodesService.removeResetPasswordCode(resetPasswordDto);
        return updatePassword(resetPasswordDto);
    }

    public ResponseEntity<?> resendingPasswordResetCode(@RequestBody SendCodeRequest sendCodeRequest){
        AppMessage message = confirmCodesService.resendingPasswordResetCode(sendCodeRequest);
        return ResponseEntity.ok(message);
    }

    public ResponseEntity<?> updatePassword(ResetPasswordDto resetPasswordDto){
        if(resetPasswordDto.getNewPassword() != null){
            if (!resetPasswordDto.getNewPassword().equals(resetPasswordDto.getConfirmPassword())) {
                return new ResponseEntity<>(
                        new AppError(HttpStatus.BAD_REQUEST.value(),
                                "Пароли не совпадают"), HttpStatus.BAD_REQUEST);
            }

            Optional<User> optionalUser = userService.findByEmail(resetPasswordDto.getEmail());
            if (optionalUser.isEmpty()) {
                return new ResponseEntity<>(
                        new AppError(HttpStatus.NOT_FOUND.value(),
                                "Пользователь не найден"), HttpStatus.NOT_FOUND);
            }

            userService.changePassword(optionalUser, resetPasswordDto.getNewPassword());
        }
        return ResponseEntity.ok(new AppMessage("Сброс пароля прошло успешно"));
    }
}
