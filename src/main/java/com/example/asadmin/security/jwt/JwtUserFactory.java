package com.example.asadmin.security.jwt;

import com.example.asadmin.model.User;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getEmail(),
                user.getFullName(),
                user.getPassword(),
                user.getRoles()
        );
    }
}