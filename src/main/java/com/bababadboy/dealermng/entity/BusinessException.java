package com.bababadboy.dealermng.entity;

/**
 * @author wangxiaobin
 * @description: 业务异常类
 * @create 2018-12-03 下午1:02
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
