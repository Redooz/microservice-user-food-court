package com.pragma.userfoodcourt.domain.api;

import com.pragma.userfoodcourt.domain.model.User;

public interface IUserServicePort {
    void saveUser(User user);
    User findUserByDocumentId(String documentId);
}
