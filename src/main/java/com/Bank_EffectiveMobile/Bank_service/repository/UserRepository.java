package com.Bank_EffectiveMobile.Bank_service.repository;

import com.Bank_EffectiveMobile.Bank_service.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, CustomUserRepository {
    UserEntity findBankUserByLogin(String login);
//    BankUser findBankUserByNumber(String number);
//    BankUser findBankUserByEmail(String email);

}
