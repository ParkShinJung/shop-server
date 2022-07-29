package com.example.shop.common.exception;

import com.example.shop.common.consts.ErrorConst;
import com.example.shop.common.consts.ErrorConst;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@EqualsAndHashCode(callSuper = true)
@Data
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{
    private String errorMessage;
    private String errorCode;

    public NotFoundException() {
        this.errorMessage = ErrorConst.INVALID_REQUEST_DATA.getMessage();
        this.errorCode = ErrorConst.INVALID_REQUEST_DATA.getCode();
    }

    public NotFoundException(ErrorConst.ErrorCode errorCode) {
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorCode.getMessage();
    }
}
