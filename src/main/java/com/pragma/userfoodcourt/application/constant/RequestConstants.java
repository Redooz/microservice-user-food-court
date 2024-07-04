package com.pragma.userfoodcourt.application.constant;

public class RequestConstants {
    private RequestConstants() {
        throw new IllegalStateException("Utility class");
    }

    // Validation messages
    public static final String MSG_BIRTH_DATE_REQUIRED = "Birth date is required";
    public static final String MSG_DOCUMENT_ID_REQUIRED = "Document ID is required";
    public static final String MSG_DOCUMENT_ID_NUMBER = "Document ID must be a number";
    public static final String MSG_EMAIL_REQUIRED = "Email is required";
    public static final String MSG_INVALID_EMAIL = "Invalid email";
    public static final String MSG_LAST_NAME_REQUIRED = "Last name is required";
    public static final String MSG_NAME_REQUIRED = "Name is required";
    public static final String MSG_PASSWORD_REQUIRED = "Password is required";
    public static final String MSG_PHONE_REQUIRED = "Phone is required";
    public static final String MSG_PHONE_FORMAT = "Phone must be in the format +11234567890";

    // Regular expressions
    public static final String REGEX_DOCUMENT_ID = "^\\d+$";
    public static final String REGEX_PHONE = "^\\+\\d{11,12}$";
}