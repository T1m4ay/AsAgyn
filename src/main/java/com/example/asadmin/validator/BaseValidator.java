package com.example.asadmin.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

public abstract class BaseValidator<T> {

    @Autowired
    private MessageSource messageSource;

    public abstract ValidatorResponse<T> validate(T t);

    protected String getLocalizedMessage(String messageCode, Locale locale) {
        return messageSource.getMessage(messageCode, null, locale);
    }

    public String getLocalizedMessage(String messageCode) {
        return getLocalizedMessage(messageCode, LocaleContextHolder.getLocale());
    }
}
