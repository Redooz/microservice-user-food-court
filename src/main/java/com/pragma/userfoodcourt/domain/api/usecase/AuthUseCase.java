package com.pragma.userfoodcourt.domain.api.usecase;

import com.pragma.userfoodcourt.domain.api.IAuthServicePort;
import com.pragma.userfoodcourt.domain.api.IJwtServicePort;
import com.pragma.userfoodcourt.domain.api.IUserServicePort;
import com.pragma.userfoodcourt.domain.constant.AuthConstants;
import com.pragma.userfoodcourt.domain.exception.InvalidPasswordException;
import com.pragma.userfoodcourt.domain.model.Role;
import com.pragma.userfoodcourt.domain.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthUseCase implements IAuthServicePort {
    private final IUserServicePort userServicePort;
    private final PasswordEncoder passwordEncoder;
    private final IJwtServicePort jwtServicePort;

    public AuthUseCase(IUserServicePort userServicePort, PasswordEncoder passwordEncoder, IJwtServicePort jwtServicePort) {
        this.userServicePort = userServicePort;
        this.passwordEncoder = passwordEncoder;
        this.jwtServicePort = jwtServicePort;
    }

    @Override
    public void registerRestaurantOwner(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.OWNER);

        userServicePort.saveUser(user);
    }

    @Override
    public String login(String email, String password) {
        User user = userServicePort.findUserByEmail(email);
        boolean isPasswordMatch = passwordEncoder.matches(password, user.getPassword());

        if (!isPasswordMatch) {
            throw new InvalidPasswordException(AuthConstants.INVALID_PASSWORD_EXCEPTION_MESSAGE);
        }

        return jwtServicePort.generateToken(user);
    }
}
