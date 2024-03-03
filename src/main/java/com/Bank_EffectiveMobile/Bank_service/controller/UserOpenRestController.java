package com.Bank_EffectiveMobile.Bank_service.controller;

import com.Bank_EffectiveMobile.Bank_service.exception.BadRequestParametarsException;
import com.Bank_EffectiveMobile.Bank_service.exception.UserAlreadyExistsException;
import com.Bank_EffectiveMobile.Bank_service.model.DTO.ConvertorUserDtoToEntity;
import com.Bank_EffectiveMobile.Bank_service.model.DTO.UserDTO;
import com.Bank_EffectiveMobile.Bank_service.model.entity.UserEntity;
import com.Bank_EffectiveMobile.Bank_service.service.UserRepoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/open")
public class UserOpenRestController {
    @Autowired
    private UserRepoService userRepoService;

    @PostMapping
    public UserEntity addNewUserWithForm(@Valid @RequestBody UserDTO user){
        log.info("user: " + user.toString());
        isUserNotExist(user);

        return userRepoService.addNewUser(ConvertorUserDtoToEntity.convertCreatedUserDtoToEntity(user));
    }

//    private boolean isRequestValid(final UserDTO user) {
//        if (user.getLogin().length()>16 || user.getLogin() == null){
//            throw new BadRequestParametarsException("login is invalid");
//        } else if (user.getPassword().length()>16 || user.getPassword() == null) {
//            throw new BadRequestParametarsException("password is invalid");
//        } else if (user.getNumber().length()!=11 || user.getNumber() == null) {
//            throw new BadRequestParametarsException("number is invalid");
//        } else if (user.getEmail() == null || user.getEmail().length() > 64) {
//            throw new BadRequestParametarsException("startSum is invalid");
//        } else {
//            return true;
//        }
//    }

    private boolean isUserNotExist(final UserDTO user) {
        String login = user.getLogin();
        String number = user.getNumber();
        String email = user.getEmail();

        List<String> listOfExistParams = userRepoService.getUsersWithCreatingParameters(login, number, email);
        if (listOfExistParams == null || listOfExistParams.isEmpty()){
            return true;
        } else if (listOfExistParams.contains(login)){
            throw new UserAlreadyExistsException("user with login: " + login + " already exists");
        } else if (listOfExistParams.contains(number)) {
            throw new UserAlreadyExistsException("user with login: " + number + " already exists");
        } else {
            throw new UserAlreadyExistsException("user with login: " + email + " already exists");
        }
    }




    /**
     * ExceptionHandlers
     * @BadRequestParametarsException
     * @UserAlreadyExistsException
     * @HttpMessageNotReadableException
     */

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(BadRequestParametarsException.class)
//    private ResponseEntity<String> BadRequestParametersExceptionHandler(BadRequestParametarsException exception){
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
//    }

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
