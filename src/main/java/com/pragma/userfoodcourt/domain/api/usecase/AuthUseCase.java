package com.pragma.userfoodcourt.domain.api.usecase;

import com.pragma.userfoodcourt.domain.api.IAuthServicePort;
import com.pragma.userfoodcourt.domain.api.IUserServicePort;
import com.pragma.userfoodcourt.domain.model.Role;
import com.pragma.userfoodcourt.domain.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthUseCase implements IAuthServicePort {
    private final IUserServicePort userServicePort;
    private final PasswordEncoder passwordEncoder;

    public AuthUseCase(IUserServicePort userServicePort, PasswordEncoder passwordEncoder) {
        this.userServicePort = userServicePort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerRestaurantOwner(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.OWNER);

        userServicePort.saveUser(user);
    }
}
