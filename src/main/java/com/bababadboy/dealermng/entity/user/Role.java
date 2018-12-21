package com.bababadboy.dealermng.entity.user;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author wangxiaobin
 */
public enum Role implements GrantedAuthority {
    ROLE_ADMIN, ROLE_CLIENT;

    @Override
    public String getAuthority() {
        return name();
    }

}
