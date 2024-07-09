package com.pragma.userfoodcourt.infrastructure.driving.http.controller;

import com.pragma.userfoodcourt.application.dto.request.LoginRequest;
import com.pragma.userfoodcourt.application.dto.request.RegisterEmployeeRequest;
import com.pragma.userfoodcourt.application.dto.request.RegisterOwnerRequest;
import com.pragma.userfoodcourt.application.dto.response.AuthResponse;
import com.pragma.userfoodcourt.application.handler.AuthHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthRestControllerAdapterTest {

    @Mock
    private AuthHandler authHandler;

    private AuthRestControllerAdapter authRestControllerAdapter;

    @BeforeEach
    void setUp() {
        authHandler = mock(AuthHandler.class);
        authRestControllerAdapter = new AuthRestControllerAdapter(authHandler);
    }

    @Test
    void registerOwnerReturnsCreatedStatus() {
        RegisterOwnerRequest registerOwnerRequest = RegisterOwnerRequest.builder()
                .birthDate(LocalDate.now().minusYears(10))
                .documentId("123456")
                .email("test@email.com")
                .lastName("lastName")
                .name("name")
                .password("password")
                .phone("123456")
                .build();
        ResponseEntity<Void> response = authRestControllerAdapter.registerOwner(registerOwnerRequest);

        verify(authHandler, times(1)).registerOwner(registerOwnerRequest);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void registerEmployeeReturnsCreatedStatus() {
        RegisterEmployeeRequest registerEmployeeRequest = RegisterEmployeeRequest.builder()
                .documentId("123456")
                .email("test@test.com")
                .lastName("lastName")
                .name("name")
                .password("password")
                .phone("123456")
                .build();
        ResponseEntity<Void> response = authRestControllerAdapter.registerEmployee(registerEmployeeRequest);

        verify(authHandler, times(1)).registerEmployee(registerEmployeeRequest);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }


    @Test
    void loginReturnsOkStatus() {
        LoginRequest loginRequest = LoginRequest.builder()
                .email("test@test.com")
                .password("password")
                .build();
        when(authHandler.login(any())).thenReturn(new AuthResponse("token"));

        ResponseEntity<AuthResponse> response = authRestControllerAdapter.login(loginRequest);

        verify(authHandler, times(1)).login(loginRequest);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        String token = Objects.requireNonNull(response.getBody()).token();
        assertEquals("token", token);
    }
}