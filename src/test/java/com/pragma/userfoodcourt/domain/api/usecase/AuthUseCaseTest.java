package com.pragma.userfoodcourt.domain.api.usecase;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.pragma.userfoodcourt.domain.api.IJwtServicePort;
import com.pragma.userfoodcourt.domain.api.IUserServicePort;
import com.pragma.userfoodcourt.domain.builder.UserBuilder;
import com.pragma.userfoodcourt.domain.exception.InvalidPasswordException;
import com.pragma.userfoodcourt.domain.model.Role;
import com.pragma.userfoodcourt.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.security.crypto.password.PasswordEncoder;

class AuthUseCaseTest {

    private IUserServicePort userServicePort;
    private PasswordEncoder passwordEncoder;
    private IJwtServicePort jwtServicePort;
    private AuthUseCase authUseCase;

    @BeforeEach
    public void setUp() {
        userServicePort = mock(IUserServicePort.class);
        passwordEncoder = mock(PasswordEncoder.class);
        jwtServicePort = mock(IJwtServicePort.class);
        authUseCase = new AuthUseCase(userServicePort, passwordEncoder, jwtServicePort);
    }

    @Test
    void registerUserSuccessfully() {
        // Arrange
        User user = new UserBuilder().setPassword("rawPassword").createUser();
        Role role = Role.OWNER;

        String encodedPassword = "encodedPassword";
        when(passwordEncoder.encode("rawPassword")).thenReturn(encodedPassword);

        // Act
        authUseCase.registerUser(user, role);

        // Assert
        assertEquals(encodedPassword, user.getPassword());

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userServicePort).saveUser(userCaptor.capture());

        User capturedUser = userCaptor.getValue();
        assertEquals(encodedPassword, capturedUser.getPassword());
        assertEquals(role, capturedUser.getRole());
    }

    @Test
    void loginSuccessfully() {
        // Arrange
        User user = new UserBuilder().setPassword("rawPassword").createUser();
        when(userServicePort.findUserByEmail("email")).thenReturn(user);
        when(passwordEncoder.matches("rawPassword", user.getPassword())).thenReturn(true);

        String token = "token";
        when(jwtServicePort.generateToken(user)).thenReturn(token);

        // Act
        String result = authUseCase.login("email", "rawPassword");

        // Assert
        assertEquals(token, result);
    }

    @Test
    void loginWithInvalidPassword() {
        // Arrange
        User user = new UserBuilder().setPassword("rawPassword").createUser();
        when(userServicePort.findUserByEmail("email")).thenReturn(user);
        when(passwordEncoder.matches("rawPassword", user.getPassword())).thenReturn(false);

        // Act & Assert
        assertThrows(InvalidPasswordException.class, () -> authUseCase.login("email", "rawPassword"));
    }
}
