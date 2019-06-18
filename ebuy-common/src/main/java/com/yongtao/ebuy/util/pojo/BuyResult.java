package com.yongtao.ebuy.util.pojo;

import java.io.Serializable;

public class BuyResult implements Serializable
{
    private int status;
    private String msg;
    private Object data;


    public BuyResult(int status) {
        this.status = status;
    }

    public BuyResult(int status, Object data) {
        this.status = status;
        this.data = data;
    }

    public static BuyResult ok() {
        return new BuyResult(200);
    }


    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
