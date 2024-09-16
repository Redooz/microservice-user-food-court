package com.pragma.userfoodcourt.application.handler;

import com.pragma.userfoodcourt.application.dto.request.LoginRequest;
import com.pragma.userfoodcourt.application.dto.request.RegisterCustomerRequest;
import com.pragma.userfoodcourt.application.dto.request.RegisterEmployeeRequest;
import com.pragma.userfoodcourt.application.dto.request.RegisterOwnerRequest;
import com.pragma.userfoodcourt.application.dto.response.AuthResponse;
import com.pragma.userfoodcourt.application.mapper.IAuthDtoMapper;
import com.pragma.userfoodcourt.domain.api.IAuthServicePort;
import com.pragma.userfoodcourt.domain.model.Role;
import com.pragma.userfoodcourt.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthHandler {
    private final IAuthServicePort authServicePort;
    private final IAuthDtoMapper authRequestMapper;

    public void registerOwner(RegisterOwnerRequest request) {
        authServicePort.registerUser(authRequestMapper.toModelFromRegisterOwnerReq(request), Role.OWNER);
    }

    public void registerEmployee(RegisterEmployeeRequest request) {
        User owner = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User employee = authRequestMapper.toModelFromRegisterEmployeeReq(request);

        employee.setBoss(owner);

        authServicePort.registerUser(employee, Role.EMPLOYEE);
    }

    public void registerCustomer(RegisterCustomerRequest request) {
        authServicePort.registerUser(authRequestMapper.toModelFromRegisterCustomerReq(request), Role.CUSTOMER);
    }

    public AuthResponse login(LoginRequest request) {
        String token = authServicePort.login(request.email(), request.password());
        return new AuthResponse(token);
    }
}
