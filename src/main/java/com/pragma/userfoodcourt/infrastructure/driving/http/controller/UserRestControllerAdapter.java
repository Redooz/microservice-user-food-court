package com.pragma.userfoodcourt.infrastructure.driving.http.controller;

import com.pragma.userfoodcourt.application.dto.response.GetUserResponse;
import com.pragma.userfoodcourt.application.handler.UserHandler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User", description = "The User Endpoint")
public class UserRestControllerAdapter {
    private final UserHandler userHandler;

    @GetMapping("/{id}")
    public ResponseEntity<GetUserResponse> findUserByDocumentId(@PathVariable String id) {
        return ResponseEntity.ok(userHandler.findUserByDocumentId(id));
    }
}
