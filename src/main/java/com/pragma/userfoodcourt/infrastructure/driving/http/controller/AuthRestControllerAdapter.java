package com.pragma.userfoodcourt.infrastructure.driving.http.controller;

import com.pragma.userfoodcourt.application.dto.request.LoginRequest;
import com.pragma.userfoodcourt.application.dto.request.RegisterEmployeeRequest;
import com.pragma.userfoodcourt.application.dto.request.RegisterOwnerRequest;
import com.pragma.userfoodcourt.application.dto.response.AuthResponse;
import com.pragma.userfoodcourt.application.handler.AuthHandler;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    @PostMapping("/register/owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Owner registered successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Void> registerOwner(@RequestBody @Valid RegisterOwnerRequest registerOwnerRequest) {
        authHandler.registerOwner(registerOwnerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/register/employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee registered successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Void> registerEmployee(@RequestBody @Valid RegisterEmployeeRequest registerOwnerRequest) {
        authHandler.registerEmployee(registerOwnerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User logged in"),
            @ApiResponse(responseCode = "400", description = "Invalid data"),
    })
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest registerUserRequest) {
        return ResponseEntity.ok(authHandler.login(registerUserRequest));
    }
}
