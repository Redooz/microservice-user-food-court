package com.pragma.userfoodcourt.infrastructure.driving.http.controller;

import com.pragma.userfoodcourt.application.dto.response.GetUserResponse;
import com.pragma.userfoodcourt.application.handler.UserHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class UserRestControllerAdapterTest {

    @Mock
    private UserHandler userHandler;

    private UserRestControllerAdapter userRestControllerAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userRestControllerAdapter = new UserRestControllerAdapter(userHandler);
    }

    @Test
    void findUserByDocumentIdReturnsUserResponse() {
        String documentId = "123456";
        GetUserResponse getUserResponse = GetUserResponse.builder().documentId(documentId).build();

        when(userHandler.findUserByDocumentId(documentId)).thenReturn(getUserResponse);

        ResponseEntity<GetUserResponse> response = userRestControllerAdapter.findUserByDocumentId(documentId);

        assertEquals(ResponseEntity.ok(getUserResponse), response);
    }
}