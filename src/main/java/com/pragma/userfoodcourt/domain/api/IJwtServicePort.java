package com.pragma.userfoodcourt.domain.api;

import org.springframework.security.core.userdetails.UserDetails;

public interface IJwtServicePort {
    String generateToken(UserDetails email);
}
