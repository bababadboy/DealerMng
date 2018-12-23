package com.bababadboy.dealermng.entity;

/**
 * @author Ash
 * @date 2018/12/22 19:41
 */
public enum AuthorityName {
    ROLE_USER, ROLE_ADMIN;

    public String getAuthority() {
        return name();
    }
}