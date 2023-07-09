package com.yemeksepeti.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {
    USER_NOT_FOUND(5100,"Böyle bir kullanıcı Bulunmaadı",HttpStatus.NOT_FOUND),
    INVALID_CODE(4101,"Geçersiz kod",HttpStatus.BAD_REQUEST),
    INTERNAL_ERROR(6000,"Sunucuda Hata",HttpStatus.INTERNAL_SERVER_ERROR),

    ALREADY_ACTIVE(4200,"Hesabınız Zaten Aktif",HttpStatus.BAD_REQUEST),

    ACCOUNT_NOT_ACTIVE(4100,"hESABINIZ AKTIF DEGIL",HttpStatus.BAD_REQUEST);
    int code;
    String message;
    HttpStatus httpStatus;
}