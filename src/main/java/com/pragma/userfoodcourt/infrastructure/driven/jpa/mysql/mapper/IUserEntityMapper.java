package com.pragma.userfoodcourt.infrastructure.driven.jpa.mysql.mapper;

import com.pragma.userfoodcourt.domain.model.User;
import com.pragma.userfoodcourt.infrastructure.driven.jpa.mysql.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IUserEntityMapper {
    UserEntity toEntity(User user);

    @Mapping(target = "authorities", ignore = true)
    User toModel(UserEntity userEntity);
}
