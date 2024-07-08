package com.pragma.userfoodcourt.application.dto.request;

import com.pragma.userfoodcourt.application.constant.AuthReqConstants;
import lombok.Builder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Builder
public record RegisterOwnerRequest(
        LocalDate birthDate,

        @NotBlank(message = AuthReqConstants.MSG_DOCUMENT_ID_REQUIRED)
        @Pattern(regexp = AuthReqConstants.REGEX_DOCUMENT_ID, message = AuthReqConstants.MSG_DOCUMENT_ID_NUMBER)
        String documentId,

        @NotBlank(message = AuthReqConstants.MSG_EMAIL_REQUIRED)
        @Email(message = AuthReqConstants.MSG_INVALID_EMAIL)
        String email,

        @NotBlank(message = AuthReqConstants.MSG_LAST_NAME_REQUIRED)
        String lastName,

        @NotBlank(message = AuthReqConstants.MSG_NAME_REQUIRED)
        String name,

        @NotBlank(message = AuthReqConstants.MSG_PASSWORD_REQUIRED)
        String password,

        @NotBlank(message = AuthReqConstants.MSG_PHONE_REQUIRED)
        @Pattern(regexp = AuthReqConstants.REGEX_PHONE, message = AuthReqConstants.MSG_PHONE_FORMAT)
        String phone
){}