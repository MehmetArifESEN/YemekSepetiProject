package com.yemeksepeti.exceptions;

import lombok.Getter;

@Getter
public class YemekSepetiException extends RuntimeException{
    private final ErrorType  errorType;

    public YemekSepetiException(ErrorType errorType) {
        this.errorType = errorType;
    }
    public YemekSepetiException(ErrorType errorType, String customMessage) {
        super(customMessage);
        this.errorType = errorType;
    }
}
