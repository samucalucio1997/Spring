package com.app.vdc.demo.Security;

public class ErrorUser {
    private int status;
    private String errMessage;
    
    public ErrorUser() {
    }

    public ErrorUser(int status, String errMessage) {
        this.status = status;
        this.errMessage = errMessage;
    }

    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getErrMessage() {
        return errMessage;
    }
    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}
