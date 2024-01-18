package com.accenture.userservice.model;

public enum ErrorCodeEnum {
    EMAIL_EXISTS("Email address already exists"),
    EMAIL_INVALID("Email address is invalid"),
    USER_NOT_FOUND("User not found,id={0}"),
    ORGANISATION_NOT_FOUND("Organisation not found "),


    ORGANISATION_DOMAIN_EXISTS("Organisation domain is already exists"),
    SUCCESS("SUCCESS"),
    TOTAL_ATTEMPTS_OVER("Total attempts over for email verification"),
    TOKEN_MISMATCH("TOKEN_MISMATCH"),
    CODE_EXPIRED("CODE_EXPIRED"),
    GENERAL_ERROR("Internal Server Error");


    private String template;

    ErrorCodeEnum(String str) {
        this.template = str;
    }

    public String getTemplate() {
        return template;
    }

}