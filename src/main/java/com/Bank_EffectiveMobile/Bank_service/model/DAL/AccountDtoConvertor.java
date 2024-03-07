package com.Bank_EffectiveMobile.Bank_service.model.DAL;

import com.Bank_EffectiveMobile.Bank_service.model.entity.AccountEntity;

public class AccountDtoConvertor {

    public static AccountEntity convertAccountDtoToEntity(final AccountDTO dto){
        AccountEntity entity = new AccountEntity();
        entity.setSum(dto.getSum());

        return entity;
    }
}
