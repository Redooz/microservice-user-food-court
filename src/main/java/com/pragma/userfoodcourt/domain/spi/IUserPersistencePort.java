package com.pragma.userfoodcourt.domain.spi;

import com.pragma.userfoodcourt.domain.model.User;

import java.util.Optional;

public interface IUserPersistencePort {
    void saveUser(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findByDocumentId(String documentId);
}
