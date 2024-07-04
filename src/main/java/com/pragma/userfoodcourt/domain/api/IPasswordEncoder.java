package com.pragma.userfoodcourt.domain.api;

public interface IPasswordEncoder {
    String encode(CharSequence password);
    boolean matches(CharSequence rawPassword, String encodedPassword);
    default boolean upgradeEncoding(String encodedPassword) {
        return false;
    }
}
