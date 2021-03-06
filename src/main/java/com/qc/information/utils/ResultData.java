package com.qc.information.utils;

import lombok.Data;

/**
 * @Author: czt
 * @Date: 18-10-30 下午4:45
 */
@Data
public class ResultData<T> {
    private Integer status;// 状态码
    private String msg = "";// 提示信息
    private T data;// 返回数据

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
}