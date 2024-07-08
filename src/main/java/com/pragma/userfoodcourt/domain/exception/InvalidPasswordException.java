package com.pragma.userfoodcourt.domain.exception;

import com.pragma.userfoodcourt.domain.constant.AuthConstants;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String message) {
        super(message);
    }
}
