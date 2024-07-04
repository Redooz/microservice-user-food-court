package com.pragma.userfoodcourt.application.handler;

import com.pragma.userfoodcourt.application.dto.request.RegisterOwnerRequest;
import com.pragma.userfoodcourt.application.mapper.IAuthRequestMapper;
import com.pragma.userfoodcourt.domain.api.IAuthServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthHandler {
    private final IAuthServicePort authServicePort;
    private final IAuthRequestMapper authRequestMapper;

    public void registerOwner(RegisterOwnerRequest request) {
        authServicePort.registerRestaurantOwner(authRequestMapper.toModel(request));
    }
}
