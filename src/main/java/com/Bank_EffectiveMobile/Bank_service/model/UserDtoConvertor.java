package com.Bank_EffectiveMobile.Bank_service.model;

import com.Bank_EffectiveMobile.Bank_service.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserDtoConvertor {
    public static UserEntity convertUserDtoToEntity(final UserDTO userDto){
        UserEntity entity = new UserEntity();
        entity.setName(userDto.getName());
        entity.setLastName(userDto.getLastName());
        entity.setSurname(userDto.getSurname());
        entity.setDateOfBirth(userDto.getDateOfBirth());
        entity.setLogin(userDto.getLogin());
        entity.setPassword(userDto.getPassword());
        entity.setNumbers(userDto.getNumber());
        entity.setEmails(userDto.getEmail());
        entity.setAccount(AccountDtoConvertor.convertAccountDtoToEntity(userDto.getAccount()));

        return entity;
    }

}
