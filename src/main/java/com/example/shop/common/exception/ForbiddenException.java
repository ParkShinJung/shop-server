package com.example.shop.common.exception;

import com.example.shop.common.consts.ErrorConst;
import com.example.shop.common.consts.ErrorConst;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@EqualsAndHashCode(callSuper=false)
@Data
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException{
    private String errorMessage;
    private String errorCode;

    public ForbiddenException() {
        this.errorMessage = ErrorConst.INVALID_REQUEST_DATA.getMessage();
        this.errorCode = ErrorConst.INVALID_REQUEST_DATA.getCode();
    }

    public ForbiddenException(ErrorConst.ErrorCode errorCode) {
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorCode.getMessage();
    }
}
