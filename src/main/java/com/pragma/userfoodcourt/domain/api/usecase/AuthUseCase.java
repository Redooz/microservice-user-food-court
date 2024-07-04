package com.pragma.userfoodcourt.domain.api.usecase;

import com.pragma.userfoodcourt.domain.api.IAuthServicePort;
import com.pragma.userfoodcourt.domain.api.IPasswordEncoder;
import com.pragma.userfoodcourt.domain.api.IUserServicePort;
import com.pragma.userfoodcourt.domain.model.Role;
import com.pragma.userfoodcourt.domain.model.User;

public class AuthUseCase implements IAuthServicePort {
    private final IUserServicePort userServicePort;
    private final IPasswordEncoder passwordEncoder;

    public AuthUseCase(IUserServicePort userServicePort, IPasswordEncoder passwordEncoder) {
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
