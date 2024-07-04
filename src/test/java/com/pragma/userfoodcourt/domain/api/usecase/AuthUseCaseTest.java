package com.pragma.userfoodcourt.domain.api.usecase;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.pragma.userfoodcourt.domain.api.IUserServicePort;
import com.pragma.userfoodcourt.domain.builder.UserBuilder;
import com.pragma.userfoodcourt.domain.model.Role;
import com.pragma.userfoodcourt.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.security.crypto.password.PasswordEncoder;

class AuthUseCaseTest {

    private IUserServicePort userServicePort;
    private PasswordEncoder passwordEncoder;
    private AuthUseCase authUseCase;

    @BeforeEach
    public void setUp() {
        userServicePort = mock(IUserServicePort.class);
        passwordEncoder = mock(PasswordEncoder.class);
        authUseCase = new AuthUseCase(userServicePort, passwordEncoder);
    }

    @Test
    void registerRestaurantOwnerSuccessfully() {
        // Arrange
        User user = new UserBuilder().setPassword("rawPassword").createUser();

        String encodedPassword = "encodedPassword";
        when(passwordEncoder.encode("rawPassword")).thenReturn(encodedPassword);

        // Act
        authUseCase.registerRestaurantOwner(user);

        // Assert
        assertEquals(encodedPassword, user.getPassword());
        assertEquals(Role.OWNER, user.getRole());

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userServicePort).saveUser(userCaptor.capture());

        User capturedUser = userCaptor.getValue();
        assertEquals(encodedPassword, capturedUser.getPassword());
        assertEquals(Role.OWNER, capturedUser.getRole());
    }
}
