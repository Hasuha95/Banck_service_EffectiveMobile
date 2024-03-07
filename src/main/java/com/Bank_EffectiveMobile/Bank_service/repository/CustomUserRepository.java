package com.Bank_EffectiveMobile.Bank_service.repository;

import com.Bank_EffectiveMobile.Bank_service.model.entity.UserEntity;

import java.time.LocalDate;
import java.util.List;

public interface CustomUserRepository {
    List<String> isUserExist(String login, String number, String email);
    List<String> canBeUpdate(String login, List<String> number, List<String> email);

    List<UserEntity> findUserByDate(LocalDate date);

    List<UserEntity> findUserByNumber(String number);

    List<UserEntity> findUserByFullName(String name, String lastName, String surname);

    List<UserEntity> findUserByEmail(String number);

    UserEntity findUserWithAnyNumber(List<String> numbers);
}
