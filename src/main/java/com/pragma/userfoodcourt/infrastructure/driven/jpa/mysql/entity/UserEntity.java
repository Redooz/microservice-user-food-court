package com.pragma.userfoodcourt.infrastructure.driven.jpa.mysql.entity;

import com.pragma.userfoodcourt.domain.constant.UserConstants;
import com.pragma.userfoodcourt.domain.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserEntity {
    @Id
    private String documentId;

    private String name;

    private String lastName;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(unique = true)
    private String email;

    private String password;

    @Column(length = UserConstants.MAX_PHONE_LENGTH)
    private String phone;
}
