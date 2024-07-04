package com.pragma.userfoodcourt.configuration;

import com.pragma.userfoodcourt.domain.api.IAuthServicePort;
import com.pragma.userfoodcourt.domain.api.IUserServicePort;
import com.pragma.userfoodcourt.domain.api.usecase.AuthUseCase;
import com.pragma.userfoodcourt.domain.api.usecase.UserUseCase;
import com.pragma.userfoodcourt.domain.spi.IUserPersistencePort;
import com.pragma.userfoodcourt.infrastructure.driven.jpa.mysql.adapter.UserPersistenceAdapter;
import com.pragma.userfoodcourt.infrastructure.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.pragma.userfoodcourt.infrastructure.driven.jpa.mysql.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AuthBeanConfiguration {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final ApplicationBeanConfiguration applicationBeanConfig;

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserPersistenceAdapter(userRepository, userEntityMapper);
    }

    @Bean
    public IUserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort());
    }

    @Bean
    public IAuthServicePort authServicePort() {
        return new AuthUseCase(
                userServicePort(),
                applicationBeanConfig.passwordEncoder()
        );
    }
}
