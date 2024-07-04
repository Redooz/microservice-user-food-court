package com.pragma.userfoodcourt.domain.exception;

import com.pragma.userfoodcourt.domain.constant.UserConstants;

public class UserEmailExistsException extends RuntimeException {
    public UserEmailExistsException(String email) {
        super(String.format(UserConstants.USER_EMAIL_EXISTS_MESSAGE, email));
    }
}