package com.pragma.userfoodcourt.application.mapper;

import com.pragma.userfoodcourt.application.dto.response.GetUserResponse;
import com.pragma.userfoodcourt.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserDtoMapper {
    GetUserResponse toResponse(User user);
}
