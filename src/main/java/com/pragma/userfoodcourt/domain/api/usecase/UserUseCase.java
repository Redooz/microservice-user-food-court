package com.pragma.userfoodcourt.domain.api.usecase;

import com.pragma.userfoodcourt.domain.api.IUserServicePort;
import com.pragma.userfoodcourt.domain.constant.UserConstants;
import com.pragma.userfoodcourt.domain.exception.OwnerNotAdultException;
import com.pragma.userfoodcourt.domain.exception.UserDocumentIdExistsException;
import com.pragma.userfoodcourt.domain.exception.UserEmailExistsException;
import com.pragma.userfoodcourt.domain.model.Role;
import com.pragma.userfoodcourt.domain.model.User;
import com.pragma.userfoodcourt.domain.spi.IUserPersistencePort;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public void saveUser(User user) {
        if (userPersistencePort.findByEmail(user.getEmail()).isPresent()) {
            throw new UserEmailExistsException(user.getEmail());
        }

        if (userPersistencePort.findByDocumentId(user.getDocumentId()).isPresent()) {
            throw new UserDocumentIdExistsException(user.getDocumentId());
        }

        if (user.getRole() == Role.OWNER && !isAdult(user.getBirthDate())) {
            throw new OwnerNotAdultException();
        }

        userPersistencePort.saveUser(user);
    }

    private boolean isAdult(LocalDate birthDate) {
        return birthDate.plusYears(UserConstants.OWNER_MIN_AGE).isBefore(ChronoLocalDate.from(LocalDateTime.now()));
    }
}
