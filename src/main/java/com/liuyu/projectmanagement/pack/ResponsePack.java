package com.liuyu.projectmanagement.pack;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResponsePack implements Serializable {

    private int code;

    private Object data;

    private String message;

    public ResponsePack() {
       this.code = 0;
       this.data = new ArrayList<>();
       this.message = "";
    }

    public ResponsePack(Object obj) {
        this.code = 0;
        this.data = obj;
        this.message = "";
    }

    public ResponsePack success() {
        this.code = 200;
        this.message = "success";
        return this;
    }

    public ResponsePack fail() {
        this.code = 500;
        this.message = "fail";
        return this;
    }

    public ResponsePack fail(String message) {
        this.code = 500;
        this.message = message;
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
