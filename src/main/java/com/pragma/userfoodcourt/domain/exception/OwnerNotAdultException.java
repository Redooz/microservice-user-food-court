package com.pragma.userfoodcourt.domain.exception;

import com.pragma.userfoodcourt.domain.constant.UserConstants;

public class OwnerNotAdultException extends RuntimeException {
    public OwnerNotAdultException() {
        super(UserConstants.OWNER_NOT_ADULT_MESSAGE);
    }
}