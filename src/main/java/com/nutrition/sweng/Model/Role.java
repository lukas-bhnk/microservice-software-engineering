package com.nutrition.sweng.Model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN, NORMAL, PREMIUM;

    @Override
    public String getAuthority() {
        return name();
    }
}
