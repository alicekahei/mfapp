package com.first.test.webceshi;


public class BaseResponse {
    public Boolean getSucc() {
        return succ;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Value getValue() {
        return value;
    }
    Boolean succ;
    String code,message;
    Value value;
}
