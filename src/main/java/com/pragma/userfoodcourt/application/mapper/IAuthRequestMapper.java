package com.pragma.userfoodcourt.application.mapper;

import com.pragma.userfoodcourt.application.dto.request.RegisterOwnerRequest;
import com.pragma.userfoodcourt.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IAuthRequestMapper {
    @Mapping(target = "role", ignore = true)
    User toModel(RegisterOwnerRequest registerOwnerRequest);
}
