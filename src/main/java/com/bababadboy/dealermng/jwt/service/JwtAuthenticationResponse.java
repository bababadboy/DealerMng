package com.bababadboy.dealermng.jwt.service;


import java.io.Serializable;

/**
 * @author Ash
 * @date 2018/12/22 20:08
 */
public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}