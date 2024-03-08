package com.Bank_EffectiveMobile.Bank_service.repository;

import com.Bank_EffectiveMobile.Bank_service.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long>, CustomUserRepository {
    Optional<UserEntity> findUserEntityByLogin(String login);
//    UserEntity findUserEntityByDate(LocalDate date);
//    BankUser findBankUserByNumber(String number);
//    BankUser findBankUserByEmail(String email);

}
