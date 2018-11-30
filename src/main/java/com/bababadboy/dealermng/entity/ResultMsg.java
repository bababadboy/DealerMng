package com.bababadboy.dealermng.entity;

/**
 * 描述:
 * Response result message
 *
 * @author wangxiaobin
 * @create 2018-11-30 下午4:00
 */
public class ResultMsg {
    private int errCode;
    private String errMsg;
    private Object data;

    public ResultMsg() {
    }

    public ResultMsg(int errCode, String errMsg, Object data) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.data = data;
    }

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
