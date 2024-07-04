package com.pragma.userfoodcourt.domain.exception;

import com.pragma.userfoodcourt.domain.constant.UserConstants;

public class UserDocumentIdExistsException extends RuntimeException {
    public UserDocumentIdExistsException(String documentId) {
        super(String.format(UserConstants.USER_DOCUMENT_ID_EXISTS_MESSAGE, documentId));
    }
}