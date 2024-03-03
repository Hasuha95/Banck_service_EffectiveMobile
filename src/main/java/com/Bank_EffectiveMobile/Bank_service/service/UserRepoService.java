package com.Bank_EffectiveMobile.Bank_service.service;

import com.Bank_EffectiveMobile.Bank_service.model.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Bank_EffectiveMobile.Bank_service.repository.UserRepository;

import java.util.List;

@Service
public class UserRepoService{
    @Autowired
    private UserRepository repository;

    public UserEntity addNewUser(final UserEntity user){
        return repository.save(user);
    }

    public UserEntity getUserById(final long id){
        return repository.findById(id).get();
    }

    public UserEntity getUserByLogin(final String login){
        return repository.findBankUserByLogin(login);
    }

    public UserEntity getUserByNumber(final String number){
//        return repository.findBankUserByNumber(number);
        return null;
    }

    public UserEntity getUserByEmail(final String email){
//        return repository.findBankUserByEmail(email);
        return null;
    }

    public List<String> getUsersWithCreatingParameters(String login, String number, String email){
//        return repository.findUsersWithCreatingParameters(login, number, email);
        return null;
    }
}
