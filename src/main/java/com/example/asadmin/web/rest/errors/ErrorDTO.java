package com.example.asadmin.web.rest.errors;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ErrorDTO {

    private int status;

    private String detail;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String error;

    private LocalDateTime timestamp = LocalDateTime.now();

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<FieldErrorInfo> fieldErrors = new ArrayList<FieldErrorInfo>();

    private String stacktrace;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public List<FieldErrorInfo> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<FieldErrorInfo> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public String getStacktrace() {
        return this.stacktrace;
    }

    public void setStacktrace(String stacktrace){
        this.stacktrace = stacktrace;
    }




    @Override
    public String toString() {
        return "{" +
                " status='" + getStatus() + "'" +
                ", detail='" + getDetail() + "'" +
                ", error='" + getError() + "'" +
                ", timestamp='" + getTimestamp() + "'" +
                ", fieldErrors='" + getFieldErrors() + "'" +
                ", stacktrace='" + getStacktrace() + "'" +
                "}";
    }

}