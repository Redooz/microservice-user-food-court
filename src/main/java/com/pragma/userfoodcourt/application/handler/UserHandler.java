package com.pragma.userfoodcourt.application.handler;

import com.pragma.userfoodcourt.application.dto.response.GetUserResponse;
import com.pragma.userfoodcourt.application.mapper.IUserRequestMapper;
import com.pragma.userfoodcourt.domain.api.IUserServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserHandler {
    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;

    public GetUserResponse findUserByDocumentId(String id) {
        return userRequestMapper.toResponse(userServicePort.findUserByDocumentId(id));
    }
}
