package com.pragma.userfoodcourt.configuration;

import com.pragma.userfoodcourt.domain.builder.UserBuilder;
import com.pragma.userfoodcourt.domain.model.Role;
import com.pragma.userfoodcourt.domain.model.User;
import com.pragma.userfoodcourt.domain.api.IUserServicePort;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class AdminInitializer {
    private static final Logger log = LoggerFactory.getLogger(AdminInitializer.class);
    private final IUserServicePort userServicePort;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initializeAdmin() {
        Optional<User> adminUser = userServicePort.findUserByRole(Role.ADMIN);

        if (adminUser.isEmpty()) {
            log.warn("Admin user not found. Creating default admin user. Change the password after the first login.");

            User user = new UserBuilder()
                    .setDocumentId("00000000000")
                    .setEmail("admin@admin.com")
                    .setPassword(passwordEncoder.encode("admin"))
                    .setRole(Role.ADMIN)
                    .createUser();
            userServicePort.saveUser(user);
        }

    }
}
