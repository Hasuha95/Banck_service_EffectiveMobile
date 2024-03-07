package com.Bank_EffectiveMobile.Bank_service.controller;

import com.Bank_EffectiveMobile.Bank_service.exception.UserAlreadyExistsException;
import com.Bank_EffectiveMobile.Bank_service.model.entity.UserEntity;
import com.Bank_EffectiveMobile.Bank_service.model.DAL.UserDTO;
import com.Bank_EffectiveMobile.Bank_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/v1/close/changes")
public class DataChangesCloseRestController {
    private UserService service;

    @Autowired
    public DataChangesCloseRestController(UserService service) {
        this.service = service;
    }

    /**
     * @param user you can only change your number and email
     * @return
     */
    @PutMapping
    public UserEntity updateUserData(@Valid @RequestBody UserDTO user){
        return service.updateUserData(user);
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(UserAlreadyExistsException.class)
    private ResponseEntity<String> UserAlreadyExistsExceptionHandler(UserAlreadyExistsException exception){
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    private ResponseEntity<String> HttpMessageNotReadableExceptionHandler(HttpMessageNotReadableException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
