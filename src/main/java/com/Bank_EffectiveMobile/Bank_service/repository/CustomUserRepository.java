package com.Bank_EffectiveMobile.Bank_service.repository;

import com.Bank_EffectiveMobile.Bank_service.entity.UserEntity;

import java.time.LocalDate;
import java.util.List;

public interface CustomUserRepository {
    List<String> findUsersWithCreatingParameters(String login, String number, String email);

    List<UserEntity> findUserByDate(LocalDate date);

    List<UserEntity> findUserByNumber(String number);

    List<UserEntity> findUserByFullName(String name, String lastName, String surname);

    List<UserEntity> findUserByEmail(String number);

}
