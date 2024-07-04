package com.pragma.userfoodcourt.application.dto.request;

import com.pragma.userfoodcourt.application.constant.RequestConstants;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

public record RegisterOwnerRequest(
        LocalDate birthDate,

        @NotBlank(message = RequestConstants.MSG_DOCUMENT_ID_REQUIRED)
        @Pattern(regexp = RequestConstants.REGEX_DOCUMENT_ID, message = RequestConstants.MSG_DOCUMENT_ID_NUMBER)
        String documentId,

        @NotBlank(message = RequestConstants.MSG_EMAIL_REQUIRED)
        @Email(message = RequestConstants.MSG_INVALID_EMAIL)
        String email,

        @NotBlank(message = RequestConstants.MSG_LAST_NAME_REQUIRED)
        String lastName,

        @NotBlank(message = RequestConstants.MSG_NAME_REQUIRED)
        String name,

        @NotBlank(message = RequestConstants.MSG_PASSWORD_REQUIRED)
        String password,

        @NotBlank(message = RequestConstants.MSG_PHONE_REQUIRED)
        @Pattern(regexp = RequestConstants.REGEX_PHONE, message = RequestConstants.MSG_PHONE_FORMAT)
        String phone
){}