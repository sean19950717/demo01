package com.demo.common.exception;

import com.demo.common.utils.spring.Messages;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.MoreObjects;

import java.io.Serializable;

/**
 *
 * @author 林建伟
 */


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response implements Serializable {
    private static final int DEFAULObject_SUCCESS_CODE = 200;
    private static final int DEFAULObject_ERROR_CODE = 400;
    private Object data;
    private String msg;
    private int code;

    public static Response success(){
        Response response = initSuccess();
        return response;
    }

    public static Response success(String msg){
        Response response = initSuccess();
        response.setMsg(msg);
        return response;
    }

    public static Response success(Object data){
        Response response = initSuccess();
        response.setData(data);
        return response;
    }

    public static Response success(String msg,Object data){
        Response response = initSuccess();
        response.setMsg(msg);
        response.setData(data);
        return response;
    }

    public static Response error(){
        Response response = initError();
        return response;
    }

    public static Response error(String msg){
        Response response = initError();
        response.setMsg(msg);
        return response;
    }

    public static Response error(int code,String msg){
        Response response = initError();
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }


    public static Response error(int code,String msg,Object data){
        Response response = initError();
        response.setCode(code);
        response.setMsg(msg);
        response.setData(data);
        return response;
    }

    private static Response initSuccess(){
        Response response =  new Response();
        response.setCode(DEFAULObject_SUCCESS_CODE);
        response.setMsg(Messages.get("operation.success"));
        return response;
    }

    private static Response initError(){
        Response response =  new Response();
        response.setCode(DEFAULObject_ERROR_CODE);
        response.setMsg(Messages.get("operation.error"));
        return response;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
                .add("Hash",hashCode())
                .add("code",code).add("msg",msg).add("data",data)
                .omitNullValues().toString();
    }
}
