package com.pragma.userfoodcourt.domain.api.usecase;

import com.pragma.userfoodcourt.domain.builder.UserBuilder;
import com.pragma.userfoodcourt.domain.exception.NoDataFoundException;
import com.pragma.userfoodcourt.domain.exception.OwnerNotAdultException;
import com.pragma.userfoodcourt.domain.exception.UserDocumentIdExistsException;
import com.pragma.userfoodcourt.domain.exception.UserEmailExistsException;
import com.pragma.userfoodcourt.domain.model.Role;
import com.pragma.userfoodcourt.domain.model.User;
import com.pragma.userfoodcourt.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;

    private UserUseCase userUseCase;

    @BeforeEach
    void setUp() {
        userPersistencePort = mock(IUserPersistencePort.class);
        userUseCase = new UserUseCase(userPersistencePort);
    }

    @Test
    void saveUserSuccessfully() {
        UserBuilder userBuilder = new UserBuilder();
        User user = userBuilder.setEmail("test@test.com")
                .setDocumentId("123456")
                .setRole(Role.OWNER)
                .setBirthDate(LocalDate.now().minusYears(20)).createUser();

        when(userPersistencePort.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(userPersistencePort.findByDocumentId(user.getDocumentId())).thenReturn(Optional.empty());

        userUseCase.saveUser(user);

        verify(userPersistencePort, times(1)).saveUser(user);
    }

    @Test
    void saveUserThrowsUserEmailExistsException() {
        UserBuilder userBuilder = new UserBuilder();
        User user = userBuilder.setEmail("test@test.com").createUser();

        when(userPersistencePort.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        assertThrows(UserEmailExistsException.class, () -> userUseCase.saveUser(user));
    }

    @Test
    void saveUserThrowsUserDocumentIdExistsException() {
        UserBuilder userBuiler = new UserBuilder();

        User user = userBuiler.setEmail("test@test.com").setDocumentId("123456").createUser();

        when(userPersistencePort.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(userPersistencePort.findByDocumentId(user.getDocumentId())).thenReturn(Optional.of(user));

        assertThrows(UserDocumentIdExistsException.class, () -> userUseCase.saveUser(user));
    }

    @Test
    void saveUserThrowsOwnerNotAdultException() {
        UserBuilder userBuilder = new UserBuilder();
        User user = userBuilder.setEmail("test@test.com")
                .setDocumentId("123456")
                .setRole(Role.OWNER)
                .setBirthDate(LocalDate.now().minusYears(10))
                .createUser();

        when(userPersistencePort.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(userPersistencePort.findByDocumentId(user.getDocumentId())).thenReturn(Optional.empty());

        assertThrows(OwnerNotAdultException.class, () -> userUseCase.saveUser(user));
    }

    @Test
    void findUserByDocumentIdSuccessfully() {
        UserBuilder userBuilder = new UserBuilder();
        User user = userBuilder.setEmail("test@email.com")
                .setDocumentId("123456")
                .createUser();

        when(userPersistencePort.findByDocumentId(user.getDocumentId())).thenReturn(Optional.of(user));

        userUseCase.findUserByDocumentId(user.getDocumentId());

        verify(userPersistencePort, times(1)).findByDocumentId(user.getDocumentId());
    }

    @Test
    void findUserByDocumentIdThrowsNoDataFoundException() {
        UserBuilder userBuilder = new UserBuilder();
        User user = userBuilder.setEmail("test@email.com")
                .setDocumentId("123456")
                .createUser();

        when(userPersistencePort.findByDocumentId(user.getDocumentId())).thenReturn(Optional.empty());

        assertThrows(NoDataFoundException.class, () -> userUseCase.findUserByDocumentId(user.getDocumentId()));
    }

}