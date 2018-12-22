package com.bababadboy.dealermng.exception;

import com.bababadboy.dealermng.entity.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 描述:
 * 全局异常处理类
 *
 * @author wangxiaobin
 * @create 2018-12-02 下午3:51
 */
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理所有不可知的异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    ResponseEntity.BodyBuilder handleException(Exception e){

        LOGGER.error(e.getMessage(), e);
        return ResponseEntity.status(500);
    }

    /**
     * 处理所有业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    ResponseEntity.BodyBuilder handleBusinessException(BusinessException e){
        LOGGER.error(e.getMessage(), e);


        return ResponseEntity.status(500);
    }


}

