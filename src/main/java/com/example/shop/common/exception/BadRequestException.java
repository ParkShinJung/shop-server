package com.example.shop.common.exception;

import com.example.shop.common.consts.ErrorConst;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@EqualsAndHashCode(callSuper = true)
@Data
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{
    private String errorMessage;
    private String errorCode;

    public BadRequestException() {
        this.errorMessage = ErrorConst.INVALID_REQUEST_DATA.getMessage();
        this.errorCode = ErrorConst.INVALID_REQUEST_DATA.getCode();
    }

    public BadRequestException(ErrorConst.ErrorCode errorCode) {
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorCode.getMessage();
    }
}
