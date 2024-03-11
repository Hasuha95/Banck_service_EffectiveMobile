package com.Bank_EffectiveMobile.Bank_service.controller;

import com.Bank_EffectiveMobile.Bank_service.exception.UserAlreadyExistsException;
import com.Bank_EffectiveMobile.Bank_service.model.DAL.UserDTO;
import com.Bank_EffectiveMobile.Bank_service.model.entity.UserEntity;
import com.Bank_EffectiveMobile.Bank_service.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/open/new_user")
public class UserOpenRestController {
    @Autowired
    private UserService userRepoService;

//    {
//        "login": "user6",
//            "password": "nohcho95",
//            "numbers": ["8-932-095-55-99"],
//        "emails": ["vgvgvg@mail.ru"],
//        "account": {
//        "sum": 2323
//    }
//    }
    /**
     * @URL - http://localhost:9506/bank/api/v1/open/new_user
     * @param user
     * @return
     */
    @PostMapping
    public UserEntity addNewUserWithForm(@Valid @RequestBody UserDTO user){
        return userRepoService.addNewUser(user);
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
