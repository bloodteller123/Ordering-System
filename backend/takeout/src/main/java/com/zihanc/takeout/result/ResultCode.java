package com.zihanc.takeout.result;

public enum ResultCode {
    SUCCESS(200,"success"),
    FAILED(500, "failed");
    private int code;
    private String msg;
    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode(){
        return this.code;
    }

    public String getMsg(){
        return this.msg;
    }
}

