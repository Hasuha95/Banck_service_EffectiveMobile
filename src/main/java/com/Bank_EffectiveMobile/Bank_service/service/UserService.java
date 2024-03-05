package com.Bank_EffectiveMobile.Bank_service.service;

import com.Bank_EffectiveMobile.Bank_service.exception.BadRequestParametersException;
import com.Bank_EffectiveMobile.Bank_service.exception.UserAlreadyExistsException;
import com.Bank_EffectiveMobile.Bank_service.model.FilterParameters;
import com.Bank_EffectiveMobile.Bank_service.model.UserDTO;
import com.Bank_EffectiveMobile.Bank_service.entity.UserEntity;
import com.Bank_EffectiveMobile.Bank_service.model.UserDtoConvertor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Bank_EffectiveMobile.Bank_service.repository.UserRepository;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public UserEntity addNewUser(final UserDTO userDto) {
        ExistStatus existStatus = isUserExist(userDto);
        if (existStatus.status) {
            throw new UserAlreadyExistsException("user with such "
                    + existStatus.message + " already exists");
        } else {
            return repository.save(UserDtoConvertor.convertUserDtoToEntity(userDto));
        }
    }

    @Deprecated
    public List<UserEntity> getUserByParameters(FilterParameters params){

//        switch (params.getTypeOfFilter()){
//            case DATE -> repository.findUserByDate(params.getDate());
//        }
        return null;
    }

    public List<UserEntity> getUserByDate(final String date){
        LocalDate parsedDate;
        try {
            parsedDate = LocalDate.parse(date);
        } catch (DateTimeParseException e){
            throw new BadRequestParametersException("invalid date parameter field");
        }
        return repository.findUserByDate(parsedDate);
    }

    public UserEntity getUserByNumber(final String number){
        Pattern datePattern = Pattern.compile("\\d{1}-\\d{3}-\\d{3}-\\d{2}-\\d{2}");
        Matcher dateMatcher = datePattern.matcher(number);
        if (!dateMatcher.matches()){
            throw new BadRequestParametersException("invalid number filter parameter");
        }
        return repository.findUserByNumber(number);
    }

    public List<UserEntity> getUserByFoolName(final String foolName){
        String[] str = foolName.split(" ");
        if (str.length<3){
            throw new BadRequestParametersException("invalid foolName parameter field");
        }
        String name = str[0];
        String lastName = str[1];
        String surname = str[2];
        return repository.findUserByFoolName(name, lastName, surname);
    }

    //  данная реализация проверки валидности имеется и в анатации @Mail
    public UserEntity getUserByEmail(final String email){
        if (email.split("@").length <= 1) {
            throw new BadRequestParametersException("invalid mail filter parameter");
        }
        return repository.findUserByEmail(email);
    }

    private ExistStatus isUserExist(final UserDTO user) {
        String login = user.getLogin();
        String number = user.getNumbers().get(0);
        String email = user.getEmails().get(0);

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
