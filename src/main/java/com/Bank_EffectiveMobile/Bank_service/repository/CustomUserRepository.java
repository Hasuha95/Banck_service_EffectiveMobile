package com.Bank_EffectiveMobile.Bank_service.repository;

import com.Bank_EffectiveMobile.Bank_service.entity.UserEntity;

import java.time.LocalDate;
import java.util.List;

public interface CustomUserRepository {
    List<String> findUsersWithCreatingParameters(String login, String number, String email);

    List<UserEntity> findUserByDate(LocalDate date);

    UserEntity findUserByNumber(String number);

    List<UserEntity> findUserByFoolName(String name, String lastName, String surname);

    UserEntity findUserByEmail(String number);

}
