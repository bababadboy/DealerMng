package com.bababadboy.dealermng.exception;


/**
 * @author wangxiaobin
 */
public enum ResultStatusCode {
    OK(0,"OK"),
    SYSTEM_ERR(30001,"SYSTEM ERROR");

    private int errCode;
    private String errMsg;

    /**constructor**/

    ResultStatusCode() {
    }

    ResultStatusCode(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    /**getter and setter**/
    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
