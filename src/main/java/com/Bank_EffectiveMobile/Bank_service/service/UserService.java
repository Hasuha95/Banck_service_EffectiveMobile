package com.Bank_EffectiveMobile.Bank_service.service;

import com.Bank_EffectiveMobile.Bank_service.exception.BadRequestParametersException;
import com.Bank_EffectiveMobile.Bank_service.exception.UserAlreadyExistsException;
import com.Bank_EffectiveMobile.Bank_service.model.DAL.FilterParameters;
import com.Bank_EffectiveMobile.Bank_service.model.DAL.UserDTO;
import com.Bank_EffectiveMobile.Bank_service.model.entity.UserEntity;
import com.Bank_EffectiveMobile.Bank_service.model.DAL.UserDtoConvertor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.Bank_EffectiveMobile.Bank_service.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private final UserRepository repository;

    public UserEntity addNewUser(final UserDTO userDto) {
        ExistStatus existStatus = isUserExist(userDto);
        if (existStatus.status) {
            throw new UserAlreadyExistsException("user with such "
                    + existStatus.message + " already exists");
        } else {
            return repository.save(UserDtoConvertor.convertUserDtoToEntity(userDto));
        }
    }

    public List<UserEntity> getUserByParameters(final FilterParameters params) {
        switch (whatTypeOfFilter(params)) {
            case DATE -> {
                return repository.findUserByDate(params.getDate());
            }
            case NUMBER -> {
                return repository.findUserByNumber(params.getNumber());
            }
            case FOOL_NAME -> {
                return repository.findUserByFullName(
                        params.getName(),
                        params.getLastName(),
                        params.getSurname());
            }
            case EMAIL -> {
                return repository.findUserByEmail(params.getEmail());
            }
            default -> {
                return Collections.emptyList();
            }
        }
    }

    public UserEntity updateUserData(final UserDTO user) {
        ExistStatus existStatus = canBeUpdated(user);
        log.info("existStatus_ : " + existStatus.toString());
        if (existStatus.status){
            throw new UserAlreadyExistsException("user with such "
                    + existStatus.message + " already exists");
        }

        UserEntity entityFromDB = repository.findUserEntityByLogin(user.getLogin()).get();
        log.info("entityFromDB_ : " + entityFromDB.toString());
        if (entityFromDB != null) {
            entityFromDB.setNumbers(user.getNumbers());
            entityFromDB.setEmails(user.getEmails());
            log.info("entityFromDB_ : " + entityFromDB.toString());
            return repository.save(entityFromDB);
        } else {
            return null;
        }
    }

    public UserEntity getUserByLogin(String login) {
        log.info("login__: " + login);
        return repository
                .findUserEntityByLogin(login)
                .orElseThrow(()-> new UsernameNotFoundException(
                        String.format("user with login: '%s' nut found", login)
                ));
    }

    private ExistStatus isUserExist(final UserDTO user) {
        String login = user.getLogin();
        String number = user.getNumbers().get(0);
        String email = user.getEmails().get(0);

        List<String> listOfExistParams = repository.isUserExist(login, number, email);

        if (listOfExistParams == null) {
            return new ExistStatus("not exist", false);
        } else {
            listOfExistParams = listOfExistParams
                    .stream()
                    .flatMap(s -> Stream.of(s.split(",")))
                    .collect(Collectors.toList());
        }

        if (listOfExistParams.contains(login)) {
            return new ExistStatus("login", true);
        } else if (listOfExistParams.contains(number)) {
            return new ExistStatus("number", true);
        } else if (listOfExistParams.contains(email)) {
            return new ExistStatus("email", true);
        }
        return new ExistStatus("not exist", false);
    }

    private ExistStatus canBeUpdated(final UserDTO user){
        String login = user.getLogin();
        List<String> numbers = user.getNumbers();
        List<String> emails = user.getEmails();

        List<String> listOfExistParams = repository.canBeUpdate(login, numbers, emails);
        if (listOfExistParams == null) {
            return new ExistStatus("not exist", false);
        } else {
            listOfExistParams = listOfExistParams
                    .stream()
                    .flatMap(s -> Stream.of(s.split(",")))
                    .collect(Collectors.toList());
        }

        for (String n : numbers) {
            if (listOfExistParams.contains(n)){
                return new ExistStatus("number", true);
            }
        }

        for (String e : emails) {
            if (listOfExistParams.contains(e)){
                return new ExistStatus("email", true);
            }
        }
        return new ExistStatus("not exist", false);
    }

    private TypeOfFilter whatTypeOfFilter(final FilterParameters params) {
        log.info("FilterParameters: " + params);
        if (params.getNumber() != null) {
            return TypeOfFilter.NUMBER;
        } else if (params.getEmail() != null) {
            return TypeOfFilter.EMAIL;
        } else if (params.getDate() != null) {
            return TypeOfFilter.DATE;
        } else if (params.getName() != null
                && params.getLastName() != null
                && params.getSurname() != null) {
            return TypeOfFilter.FOOL_NAME;
        } else {
            throw new BadRequestParametersException("invalid filter parameters");
        }
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

    private enum TypeOfFilter{
        DATE,
        NUMBER,
        FOOL_NAME,
        EMAIL
    }
}
