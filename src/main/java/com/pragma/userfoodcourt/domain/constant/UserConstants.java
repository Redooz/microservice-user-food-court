package com.pragma.userfoodcourt.domain.constant;

public class UserConstants {
    private UserConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String USER_NOT_FOUND_MESSAGE = "User with document id or email  %s not found";

    public static final String USER_EMAIL_EXISTS_MESSAGE = "User with email %s already exists";
    public static final String USER_DOCUMENT_ID_EXISTS_MESSAGE = "User with document id %s already exists";
    public static final int OWNER_MIN_AGE = 18;
    public static final String OWNER_NOT_ADULT_MESSAGE = "Owner must be at least " + OWNER_MIN_AGE + " years old";

    public static final int MAX_PHONE_LENGTH = 13;
}