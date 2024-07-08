package com.pragma.userfoodcourt.application.dto.request;

import com.pragma.userfoodcourt.application.constant.AuthReqConstants;
import lombok.Builder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Builder
public record LoginRequest(
        @NotBlank(message = AuthReqConstants.MSG_EMAIL_REQUIRED)
        @Email(message = AuthReqConstants.MSG_INVALID_EMAIL)
        String email,

        @NotBlank(message = AuthReqConstants.MSG_PASSWORD_REQUIRED)
        String password
) {
}
