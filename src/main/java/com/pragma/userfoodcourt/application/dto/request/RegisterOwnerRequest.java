package com.pragma.userfoodcourt.application.dto.request;

import com.pragma.userfoodcourt.domain.model.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

public record RegisterOwnerRequest(
        @NotBlank(message = "Birth date is required")
        LocalDate birthDate,

        @NotBlank(message = "Document ID is required")
        Role role,

        @NotBlank(message = "Document ID is required")
        @Pattern(regexp = "^\\d+$", message = "Document ID must be a number")
        String documentId,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email")
        String email,

        @NotBlank(message = "Last name is required")
        String lastName,

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Password is required")String password,

        @NotBlank(message = "Phone is required")
        @Pattern(regexp = "^\\+\\d{11,12}$", message = "Phone must be in the format +11234567890")
        String phone
    ){}

