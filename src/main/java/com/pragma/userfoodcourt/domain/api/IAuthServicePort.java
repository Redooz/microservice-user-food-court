package com.pragma.userfoodcourt.domain.api;

import com.pragma.userfoodcourt.domain.model.User;

public interface IAuthServicePort {
    void registerRestaurantOwner(User user);

    void login(String email, String password);
}
