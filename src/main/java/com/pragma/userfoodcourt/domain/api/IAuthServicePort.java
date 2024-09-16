package com.pragma.userfoodcourt.domain.api;

import com.pragma.userfoodcourt.domain.model.Role;
import com.pragma.userfoodcourt.domain.model.User;

public interface IAuthServicePort {
    void registerUser(User user, Role role);

    String login(String email, String password);
}
