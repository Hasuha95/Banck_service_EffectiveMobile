package com.Bank_EffectiveMobile.Bank_service.controller;

import com.Bank_EffectiveMobile.Bank_service.entity.UserEntity;
import com.Bank_EffectiveMobile.Bank_service.model.FilterParameters;
import com.Bank_EffectiveMobile.Bank_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping(value = "/api/v2/close")
public class UserCloseRestControllerV2 {
    @Autowired
    private UserService userService;

    /*
        Реализовать поиск юзеров по различным параметрам можно несколькими способами,
    всё будет зависеть от задачи(бизнес логики). Ниже представлен один из простых вариантов.
     */

    @GetMapping(value = "/users")
    public List<UserEntity> findUsersByParameters(@RequestParam String date,
                                                  @RequestParam String number,
                                                  @RequestParam String name,
                                                  @RequestParam String lastName,
                                                  @RequestParam String surname,
                                                  @RequestParam String email){
        return userService.getUserByParameters(new FilterParameters(date, number, name, lastName, surname, email));
    }
}
