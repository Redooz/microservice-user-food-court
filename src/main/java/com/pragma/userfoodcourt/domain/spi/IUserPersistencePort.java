package com.pragma.userfoodcourt.domain.spi;

import com.pragma.userfoodcourt.domain.model.User;

public interface IUserPersistencePort {
    void saveUser(User user);
}
