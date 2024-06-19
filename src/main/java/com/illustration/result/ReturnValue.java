package com.illustration.result;

import static com.illustration.result.StateMsg.StatusMsg_200;

public class ReturnValue<T> {
    private Integer code;
    private String msg;

    public ReturnValue() {
        this.code = StatusMsg_200.getState();
        this.msg = StatusMsg_200.getMsg();
    }

    public ReturnValue(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ReturnValue(StateMsg stateMsg) {
        this.code = stateMsg.getState();
        this.msg = stateMsg.getMsg();
    }

    private T data;

    public ReturnValue(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ReturnValue(StateMsg stateMsg, T data) {
        this.code = stateMsg.getState();
        this.msg = stateMsg.getMsg();
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setStateMsg(StateMsg stateMsg) {
        this.code = stateMsg.getState();
        this.msg = stateMsg.getMsg();
    }
}
