package com.qc.information.config;

/**
 * @Author: czt
 * @Date: 18-10-30 下午4:56
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String msg) {
        super(msg);
    }
}