package com.demo.common.exception;

/**
 * Created by lin on 17-6-14.
 */
public enum ErrorKeyEnum {


    COMMON_FAIL("00000","操作失败"),
    COMMON_DELETE_FAIL("00001","operation.delete.fail"),
    COMMON_ADD_FAIL("00002","operation.add.fail"),
    COMMON_UPDATE_FAIL("00003","operation.update.fail"),
    //TODO:暂未设置对应value
    COMMON_REQUEST_PARAMETER_LESS("00001","请求参数缺失"),
    COMMON_PAGE_PARAMETER_NOT_FOUND_ERROR("00004","分页参数缺失"),
    COMMON_DATA_NOT_FOUND("00003","数据不存在"),
    COMMON_PASSWORD_EMPTY("00004","密码为空"),

    ;

    private final String errorCode;
    private final String errorKey;

    ErrorKeyEnum(String errorCode, String errorKey){
        this.errorCode = errorCode;
        this.errorKey = errorKey;
    }

    public String getErrorCode() {
        return errorCode;
    }
    public String getErrorKey() {
        return errorKey;
    }
}
