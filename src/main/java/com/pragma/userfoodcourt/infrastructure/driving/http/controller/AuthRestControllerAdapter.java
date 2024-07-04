package com.pragma.userfoodcourt.infrastructure.driving.http.controller;

import com.pragma.userfoodcourt.application.dto.request.RegisterOwnerRequest;
import com.pragma.userfoodcourt.application.handler.AuthHandler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "The Auth Endpoint")
public class AuthRestControllerAdapter {
    private final AuthHandler authHandler;

    @PostMapping("/register/admin")
    public ResponseEntity<Void> registerAdmin(@RequestBody @Valid RegisterOwnerRequest registerOwnerRequest) {
        authHandler.registerOwner(registerOwnerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
