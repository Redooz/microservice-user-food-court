package com.pragma.userfoodcourt.application.dto.response;

import com.pragma.userfoodcourt.domain.model.Role;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record GetUserResponse(
        LocalDate birthDate,
        Role role,
        String documentId,
        String email,
        String lastName,
        String name,
        String phone
) {
}
