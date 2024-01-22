package com.accenture.userservice.model;

public enum ErrorCodeEnum {
    EMAIL_EXISTS("Email address already exists :{0}"),
    EMAIL_INVALID("Email address is invalid :{0}"),
    USER_NOT_FOUND("User not found :{0}"),
    ORGANISATION_NOT_FOUND("Organisation not found :{0} "),
    ORGANISATION_DOMAIN_EXISTS("Organisation domain is already exists :{0}"),
    SUCCESS("SUCCESS"),
    TOTAL_ATTEMPTS_OVER("Total attempts over for email verification"),
    TOKEN_MISMATCH("TOKEN_MISMATCH"),
    CODE_EXPIRED("CODE_EXPIRED"),
    SERVER_ERROR("Internal Server Error");


    private String template;

    ErrorCodeEnum(String str) {
        this.template = str;
    }

    public String getTemplate() {
        return template;
    }

}