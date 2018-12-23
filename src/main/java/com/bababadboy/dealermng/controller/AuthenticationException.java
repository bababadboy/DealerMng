package com.bababadboy.dealermng.controller;

/**
 * @author Ash
 * @date 2018/12/22 19:45
 */
public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
