package com.pragma.userfoodcourt.application.mapper;

import com.pragma.userfoodcourt.application.dto.request.RegisterCustomerRequest;
import com.pragma.userfoodcourt.application.dto.request.RegisterEmployeeRequest;
import com.pragma.userfoodcourt.application.dto.request.RegisterOwnerRequest;
import com.pragma.userfoodcourt.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IAuthDtoMapper {
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    User toModelFromRegisterOwnerReq(RegisterOwnerRequest registerOwnerRequest);

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "birthDate", ignore = true)
    User toModelFromRegisterEmployeeReq(RegisterEmployeeRequest registerEmployeeRequest);

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "birthDate", ignore = true)
    User toModelFromRegisterCustomerReq(RegisterCustomerRequest registerCustomerRequest);
}
