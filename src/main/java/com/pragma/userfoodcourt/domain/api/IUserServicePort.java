package com.pragma.userfoodcourt.domain.api;

import com.pragma.userfoodcourt.domain.model.Role;
import com.pragma.userfoodcourt.domain.model.User;

import java.util.Optional;

public interface IUserServicePort {
    void saveUser(User user);
    User findUserByDocumentId(String documentId);
    User findUserByEmail(String email);

    Optional<User> findUserByRole(Role role);
}
