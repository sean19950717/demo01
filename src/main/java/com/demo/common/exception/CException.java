package com.demo.common.exception;

import com.demo.common.utils.spring.Messages;

/**
 * Created by lin on 17-6-14.
 * 自定义错误类
 */
public class CException extends RuntimeException {

    private String errorCode;
    private String errorMsg;

    public CException(ErrorKeyEnum errorKey){
        this.errorCode = errorKey.getErrorCode();
        this.errorMsg = Messages.get(errorKey.getErrorKey());
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
