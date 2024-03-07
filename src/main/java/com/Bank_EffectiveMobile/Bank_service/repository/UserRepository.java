package com.Bank_EffectiveMobile.Bank_service.repository;

import com.Bank_EffectiveMobile.Bank_service.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, CustomUserRepository {
//    UserEntity findUserEntityByDate(LocalDate date);
//    BankUser findBankUserByNumber(String number);
//    BankUser findBankUserByEmail(String email);

}
