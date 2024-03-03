package com.Bank_EffectiveMobile.Bank_service.model.DTO;

import com.Bank_EffectiveMobile.Bank_service.model.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConvertorUserDtoToEntity {

    public static UserEntity convertCreatedUserDtoToEntity(UserDTO userDto){
        UserEntity entity = new UserEntity();
        entity.setLogin(userDto.getLogin());
        entity.setPassword(userDto.getPassword());
        entity.setListOfNumbers(userDto.getNumber());
        entity.setListOfEmails(userDto.getEmail());
        if (userDto.getStartSum() != 0){
            entity.getAccount().setSum(userDto.getStartSum());
        }
        log.info("account: " + entity.getAccount().toString());
        return entity;
    }
}
