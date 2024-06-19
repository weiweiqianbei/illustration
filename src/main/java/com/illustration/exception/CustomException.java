package com.illustration.exception;

import com.illustration.result.StateMsg;

public class CustomException extends RuntimeException {
    private int code;
    private String message;

    public CustomException(StateMsg stateMsg) {
        this.code = stateMsg.getState();
        this.message = stateMsg.getMsg();
    }

    public CustomException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
