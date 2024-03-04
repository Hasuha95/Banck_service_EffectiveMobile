package com.Bank_EffectiveMobile.Bank_service.service;

import com.Bank_EffectiveMobile.Bank_service.exception.UserAlreadyExistsException;
import com.Bank_EffectiveMobile.Bank_service.model.UserDTO;
import com.Bank_EffectiveMobile.Bank_service.entity.UserEntity;
import com.Bank_EffectiveMobile.Bank_service.model.UserDtoConvertor;
import com.Bank_EffectiveMobile.Bank_service.repository.AccountRepository;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Bank_EffectiveMobile.Bank_service.repository.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public UserEntity addNewUser(final UserDTO userDto){
        ExistStatus existStatus = isUserExist(userDto);
        if (existStatus.status){
            throw new UserAlreadyExistsException("user with such "
                    + existStatus.message + " already exists");
        } else {
            return repository.save(UserDtoConvertor.convertUserDtoToEntity(userDto));
        }
    }


    //    public UserEntity getUserById(final long id){
//        return repository.findById(id).get();
//    }
//
//    public UserEntity getUserByLogin(final String login){
//        return repository.findBankUserByLogin(login);
//    }
//
//    public UserEntity getUserByNumber(final String number){
////        return repository.findBankUserByNumber(number);
//        return null;
//    }
//
//    public UserEntity getUserByEmail(final String email){
////        return repository.findBankUserByEmail(email);
//        return null;
//    }


    private ExistStatus isUserExist(final UserDTO user) {
        String login = user.getLogin();
        String number = user.getNumber();
        String email = user.getEmail();

        List<String> listOfExistParams = repository.findUsersWithCreatingParameters(login, number, email);
        if (listOfExistParams == null){
            return new ExistStatus("not exist", false);
        } else {
            listOfExistParams = listOfExistParams
                    .stream()
                    .flatMap(s -> Stream.of(s.split(",")))
                    .collect(Collectors.toList());
        }
        if (listOfExistParams.contains(login)){
            return new ExistStatus("login", true);
        } else if (listOfExistParams.contains(number)) {
            return new ExistStatus("number", true);
        } else if (listOfExistParams.contains(email)){
            return new ExistStatus("email", true);
        }
        return new ExistStatus("not exist", false);
    }

    @ToString
    private final class ExistStatus{
        private String message;
        private boolean status;

        public ExistStatus(String message, boolean status) {
            this.message = message;
            this.status = status;
        }
    }

}
