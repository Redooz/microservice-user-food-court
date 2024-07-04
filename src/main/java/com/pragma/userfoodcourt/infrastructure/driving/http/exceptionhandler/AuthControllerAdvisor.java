package com.pragma.userfoodcourt.infrastructure.driving.http.exceptionhandler;

import com.pragma.userfoodcourt.domain.exception.OwnerNotAdultException;
import com.pragma.userfoodcourt.domain.exception.UserDocumentIdExistsException;
import com.pragma.userfoodcourt.domain.exception.UserEmailExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class AuthControllerAdvisor {
    @ExceptionHandler(UserEmailExistsException.class)
    public ResponseEntity<ExceptionResponse> handleUserEmailExistsException(UserEmailExistsException e) {
        ExceptionResponse response = new ExceptionResponse(
                e.getMessage(),
                HttpStatus.BAD_REQUEST.toString(),
                LocalDateTime.now()
        );
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(UserDocumentIdExistsException.class)
    public ResponseEntity<ExceptionResponse> handleUserDocumentIdExistsException(UserDocumentIdExistsException e) {
        ExceptionResponse response = new ExceptionResponse(
                e.getMessage(),
                HttpStatus.BAD_REQUEST.toString(),
                LocalDateTime.now()
        );
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(OwnerNotAdultException.class)
    public ResponseEntity<ExceptionResponse> handleOwnerNotAdultException(OwnerNotAdultException e) {
        ExceptionResponse response = new ExceptionResponse(
                e.getMessage(),
                HttpStatus.BAD_REQUEST.toString(),
                LocalDateTime.now()
        );
        return ResponseEntity.badRequest().body(response);
    }


}
