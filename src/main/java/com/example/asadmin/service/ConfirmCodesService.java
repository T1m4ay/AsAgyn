package com.example.asadmin.service;

import com.example.asadmin.dto.*;
import com.example.asadmin.enumeration.CodeType;
import com.example.asadmin.model.ConfirmCodes;
import com.example.asadmin.repository.ConfirmCodesRepository;
import com.example.asadmin.web.rest.confirm.AppMessage;
import com.example.asadmin.web.rest.errors.ErrorDTO;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

@Service
public class ConfirmCodesService {

    private final ConfirmCodesRepository repository;
    private final EmailService emailService;
    private final MessageSource messageSource;

    private static final String CHARACTERS = "0123456789";
    private static final int CODE_LENGTH = 4;

    public ConfirmCodesService(
            ConfirmCodesRepository confirmCodesRepository,
            EmailService emailService,
            MessageSource messageSource) {
        this.repository = confirmCodesRepository;
        this.emailService = emailService;
        this.messageSource = messageSource;
    }

    public AppMessage createConfirmCode(SendCodeRequest sendCodeRequest){
        List<ConfirmCodes> codes = repository.findAllByEmailAndCodeType(
                sendCodeRequest.getEmail(),
                CodeType.register);
        if (codes.isEmpty()){
            ConfirmCodes code = new ConfirmCodes();
            code.setCode(generateVerificationCode());
            code.setEmail(sendCodeRequest.getEmail());
            code.setCreateTime(ZonedDateTime.now());
            code.setCodeType(CodeType.register);
            emailService.sendEmail(
                    code.getEmail(),
                    messageSource.getMessage(
                            "message.email.confirm.subject",
                            null,
                            LocaleContextHolder.getLocale()),
                    messageSource.getMessage(
                            "message.email.confirm.text",
                            null, LocaleContextHolder.getLocale()),
                    code.getCode());
            repository.save(code);
            return new AppMessage("Код отправлен на почту");
        }else{
            return new AppMessage("Код уже ранее был выслан на почту");
        }
    }

    public AppMessage createPasswordResetCode(SendCodeRequest sendCodeRequest){
        List<ConfirmCodes> codes = repository.findAllByEmailAndCodeType(
                sendCodeRequest.getEmail(),
                CodeType.resetPassword);
        if (codes.isEmpty()){
            ConfirmCodes code = new ConfirmCodes();
            code.setCode(generateVerificationCode());
            code.setEmail(sendCodeRequest.getEmail());
            code.setCreateTime(ZonedDateTime.now());
            code.setCodeType(CodeType.resetPassword);
            emailService.sendEmail(
                    code.getEmail(),
                    messageSource.getMessage(
                            "message.password.reset.subject",
                            null,
                            LocaleContextHolder.getLocale()),
                    messageSource.getMessage(
                            "message.password.reset.text",
                            null, LocaleContextHolder.getLocale()),
                    code.getCode());
            repository.save(code);
            return new AppMessage("Код отправлен на почту");
        }else{
            return new AppMessage("Код уже ранее был выслан на почту");
        }
    }

    public AppMessage resendingConfirmCode(SendCodeRequest sendCodeRequest){
        List<ConfirmCodes> codes = repository.findAllByEmailAndCodeType(
                sendCodeRequest.getEmail(),
                CodeType.register);
        if (!codes.isEmpty()){
            ConfirmCodes code = codes.stream()
                    .max(Comparator.comparing(ConfirmCodes::getCreateTime)).orElse(null);
            if(code == null){
                return new AppMessage("Произошла ошибка базы данных");
            }else{
                emailService.sendEmail(
                        code.getEmail(),
                        messageSource.getMessage(
                                "message.email.confirm.subject",
                                null,
                                LocaleContextHolder.getLocale()),
                        messageSource.getMessage(
                                "message.email.confirm.text",
                                null,
                                LocaleContextHolder.getLocale()),
                        code.getCode());
                return new AppMessage("Код отправлен на почту");
            }
        } else{
            return new AppMessage("Код не найден в базе данных");
        }
    }

    public AppMessage resendingPasswordResetCode(SendCodeRequest sendCodeRequest){
        List<ConfirmCodes> codes = repository.findAllByEmailAndCodeType(
                sendCodeRequest.getEmail(),
                CodeType.resetPassword);
        if (!codes.isEmpty()){
            ConfirmCodes code = codes.stream()
                    .max(Comparator.comparing(ConfirmCodes::getCreateTime)).orElse(null);
            if(code == null){
                return new AppMessage("Произошла ошибка базы данных");
            }else{
                emailService.sendEmail(
                        code.getEmail(),
                        messageSource.getMessage(
                                "message.email.confirm.subject",
                                null,
                                LocaleContextHolder.getLocale()),
                        messageSource.getMessage(
                                "message.email.confirm.text",
                                null,
                                LocaleContextHolder.getLocale()),
                        code.getCode());
                return new AppMessage("Код отправлен на почту");
            }
        } else{
            return new AppMessage("Код не найден в базе данных");
        }
    }

    public static String generateVerificationCode() {
        StringBuilder code = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            code.append(CHARACTERS.charAt(randomIndex));
        }

        return code.toString();
    }

    public ResponseDTO<ConfirmCodes> checkCode(String code, String email, CodeType codeType) {
        ResponseDTO<ConfirmCodes> responseDTO = new ResponseDTO<>();
        if (code == null){
            responseDTO.setHasErrors(true);
            responseDTO.setErrorDTO(new ErrorDTO());
            return responseDTO;
        }
        List<ConfirmCodes> codes = repository.findAllByEmailAndCodeType(email, codeType);
        ConfirmCodes codeFromDb = codes.stream()
                .max(Comparator.comparing(ConfirmCodes::getCreateTime)).orElse(null);
        responseDTO.setObject(codeFromDb);
        if (codeFromDb == null || !code.equals(codeFromDb.getCode())){
            responseDTO.setHasErrors(true);
            responseDTO.setErrorDTO(new ErrorDTO());
            return responseDTO;
        }
        responseDTO.setHasErrors(false);
        return responseDTO;
    }

    public void removeResetPasswordCode(ResetPasswordDto resetPasswordDto){
        repository.deleteAllByEmailAndCodeType(resetPasswordDto.getEmail(), CodeType.resetPassword);
    }

    public void removeConfirmCode(RegistrationUserDto registrationUserDto){
        repository.deleteAllByEmailAndCodeType(registrationUserDto.getEmail(), CodeType.register);
    }

}
