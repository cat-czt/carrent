package com.qc.information.utils;

/**
 * @Author: czt
 * @Date: 18-10-30 下午4:55
 */
public class ResultUtils {

    public static ResultData success(){
        ResultData data = new ResultData();
        data.setStatus(200);
        data.setMsg("操作成功！");
        return data;
    }

    public static ResultData error(String msg) {
        ResultData data = new ResultData();
        data.setMsg(msg);
        data.setStatus(400);
        return data;
    }
}
