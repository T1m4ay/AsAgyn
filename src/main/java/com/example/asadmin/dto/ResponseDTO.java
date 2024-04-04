package com.example.asadmin.dto;

import com.example.asadmin.web.rest.errors.ErrorDTO;
import com.example.asadmin.web.rest.errors.FieldErrorInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

public class ResponseDTO<T> {

    private T object;
    private boolean hasErrors;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<FieldErrorInfo> errors = new ArrayList<>();

    private ErrorDTO errorDTO;

    public ErrorDTO getErrorDTO() {
        return this.errorDTO;
    }

    public void setErrorDTO(ErrorDTO errorDTO) {
        this.errorDTO = errorDTO;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public boolean isHasErrors() {
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public List<FieldErrorInfo> getErrors() {
        return errors;
    }

    public void setErrors(List<FieldErrorInfo> errors) {
        this.errors = errors;
    }

    public boolean getHasErrors() {
        return hasErrors || !ObjectUtils.isEmpty(errors);
    }

    public void addError(FieldErrorInfo fieldErrorInfo) {
        if (this.getErrors() == null) {
            this.setErrors(new ArrayList<>());
        }

        this.getErrors().add(fieldErrorInfo);
    }

    public void addError(String fieldName, String description) {
        FieldErrorInfo fieldErrorInfo = new FieldErrorInfo(fieldName, description);
        if (this.getErrors() == null) {
            this.setErrors(new ArrayList<>());
        }

        this.getErrors().add(fieldErrorInfo);
    }
}