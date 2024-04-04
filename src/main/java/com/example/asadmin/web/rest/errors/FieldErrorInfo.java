package com.example.asadmin.web.rest.errors;

import com.fasterxml.jackson.annotation.JsonInclude;

public class FieldErrorInfo {

    private String fieldName;

    private String localizedFieldName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String excelFieldName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object value;

    private String description;

    public FieldErrorInfo() {}

    public FieldErrorInfo(String description) {
        this.description = description;
    }

    public FieldErrorInfo(String fieldName, String description) {
        this.fieldName = fieldName;
        this.description = description;
    }

    public FieldErrorInfo(String fieldName, String excelFieldName, String localizedFieldName, String description) {
        this.fieldName = fieldName;
        this.excelFieldName = excelFieldName;
        this.description = description;
        this.localizedFieldName = localizedFieldName;
    }

    public FieldErrorInfo(String fieldName, String excelFieldName, Object value, String description) {
        this.fieldName = fieldName;
        this.excelFieldName = excelFieldName;
        this.value = value;
        this.description = description;
    }

    public FieldErrorInfo(String fieldName, String excelFieldName, String localizedFieldName, Object value, String description) {
        this.fieldName = fieldName;
        this.excelFieldName = excelFieldName;
        this.localizedFieldName = localizedFieldName;
        this.value = value;
        this.description = description;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getExcelFieldName() {
        return excelFieldName;
    }

    public void setExcelFieldName(String excelFieldName) {
        this.excelFieldName = excelFieldName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getLocalizedFieldName() {
        return localizedFieldName;
    }

    public void setLocalizedFieldName(String localizedFieldName) {
        this.localizedFieldName = localizedFieldName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "FieldErrorInfo{" +
                "fieldName='" + fieldName + '\'' +
                ", localizedFieldName='" + localizedFieldName + '\'' +
                ", excelFieldName='" + excelFieldName + '\'' +
                ", value=" + value +
                ", description='" + description + '\'' +
                '}';
    }
}