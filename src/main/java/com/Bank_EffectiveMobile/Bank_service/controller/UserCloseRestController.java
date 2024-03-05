package com.Bank_EffectiveMobile.Bank_service.controller;

import com.Bank_EffectiveMobile.Bank_service.entity.UserEntity;
import com.Bank_EffectiveMobile.Bank_service.exception.BadRequestParametersException;
import com.Bank_EffectiveMobile.Bank_service.service.UserService;
import jakarta.persistence.NoResultException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping(value = "/api/v1/close")
public class UserCloseRestController {
    @Autowired
    private UserService userService;

    /**
     * @param date must be valid  @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}")
     * @return
     */
    @GetMapping(value = "/users/date")
    public List<UserEntity> findUsersByDate(@RequestParam String date){
        return userService.getUserByDate(date);
    }

    /**
     * @param number must be valid @Pattern(regexp = "\\d{1}-\\d{3}-\\d{3}-\\d{2}-\\d{2}")
     * @return
     */
    @GetMapping(value = "/users/number")
    public UserEntity findUsersByNumber(@RequestParam String number){
        return userService.getUserByNumber(number);
    }

    /**
     * @param foolName must consist of 3 words in the order
     *                 “last name first name patronymic”
     *                 separated by a space
     * @return
     */
    @GetMapping(value = "/users/foolName")
    public List<UserEntity> findUsersByFoolName(@RequestParam String foolName){
        return userService.getUserByFoolName(foolName);
    }

    /**
     * @param email must be valid like @Email
     * @return
     */
    @GetMapping(value = "/users/email")
    public UserEntity findUsersByEmail(@RequestParam String email){
        return userService.getUserByEmail(email);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoResultException.class)
    private ResponseEntity<String> NoResultExceptionHandler(NoResultException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no result");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestParametersException.class)
    private ResponseEntity<String> BadRequestParametersExceptionHandler(BadRequestParametersException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
