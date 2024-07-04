package com.pragma.userfoodcourt.infrastructure.driving.http.controller;

import com.pragma.userfoodcourt.application.dto.request.RegisterOwnerRequest;
import com.pragma.userfoodcourt.application.handler.AuthHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

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
        ResponseEntity<Void> response = authRestControllerAdapter.registerAdmin(registerOwnerRequest);

        verify(authHandler, times(1)).registerOwner(registerOwnerRequest);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}