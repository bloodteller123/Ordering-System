package com.zihanc.takeout.result;

public class CommonResult<T> {
    private long code;
    private String msg;
    private T data;

    public CommonResult(long code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public static <T> CommonResult<T> success(){
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), null);
    }
    public static <T> CommonResult<T> success(T data){
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), data);
    }

    public static <T> CommonResult<T> error(String msg){
        return new CommonResult<T>(ResultCode.FAILED.getCode(), msg, null);
    }
    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    //https://stackoverflow.com/a/28466881/13062745
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
}
