package com.pragma.userfoodcourt.infrastructure.driven.jpa.mysql.mapper;

import com.pragma.userfoodcourt.domain.model.User;
import com.pragma.userfoodcourt.infrastructure.driven.jpa.mysql.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserEntityMapper {
    UserEntity toEntity(User user);
    
    User toModel(UserEntity userEntity);
}
