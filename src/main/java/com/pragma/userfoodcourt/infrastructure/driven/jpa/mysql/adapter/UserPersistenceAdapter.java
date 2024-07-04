package com.pragma.userfoodcourt.infrastructure.driven.jpa.mysql.adapter;

import com.pragma.userfoodcourt.domain.model.User;
import com.pragma.userfoodcourt.domain.spi.IUserPersistencePort;
import com.pragma.userfoodcourt.infrastructure.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.pragma.userfoodcourt.infrastructure.driven.jpa.mysql.repository.IUserRepository;

import java.util.Optional;

public class UserPersistenceAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    public UserPersistenceAdapter(IUserRepository userRepository, IUserEntityMapper userEntityMapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(userEntityMapper.toEntity(user));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email).map(userEntityMapper::toModel);
    }

    @Override
    public Optional<User> findByDocumentId(String documentId) {
        return userRepository.findById(documentId).map(userEntityMapper::toModel);
    }
}
