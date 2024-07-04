package com.pragma.userfoodcourt.domain.api.usecase;

import com.pragma.userfoodcourt.domain.api.IAuthServicePort;
import com.pragma.userfoodcourt.domain.api.IUserServicePort;
import com.pragma.userfoodcourt.domain.model.Role;
import com.pragma.userfoodcourt.domain.model.User;

public class AuthUseCase implements IAuthServicePort {
    private final IUserServicePort userServicePort;

    public AuthUseCase(IUserServicePort userServicePort) {
        this.userServicePort = userServicePort;
    }

    @Override
    public void registerRestaurantOwner(User user) {
        user.setRole(Role.OWNER);

        userServicePort.saveUser(user);
    }
}
