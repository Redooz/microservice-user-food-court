package com.pragma.userfoodcourt.domain.api;

import com.pragma.userfoodcourt.domain.model.User;

public interface IAuthServicePort {
    void registerRestaurantOwner(User user);

    String login(String email, String password);
}
