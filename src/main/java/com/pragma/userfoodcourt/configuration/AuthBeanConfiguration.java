package com.pragma.userfoodcourt.configuration;

import com.pragma.userfoodcourt.configuration.security.service.JwtService;
import com.pragma.userfoodcourt.domain.api.IAuthServicePort;
import com.pragma.userfoodcourt.domain.api.IJwtServicePort;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AuthBeanConfiguration {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserPersistenceAdapter(userRepository, userEntityMapper);
    }

    @Bean
    public IUserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort());
    }

    @Bean
    public IJwtServicePort jwtServicePort() {
        return new JwtService();
    }

    @Bean
    public IAuthServicePort authServicePort() {
        return new AuthUseCase(
                userServicePort(),
                passwordEncoder(),
                jwtServicePort()
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
