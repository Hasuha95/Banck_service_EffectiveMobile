package com.Bank_EffectiveMobile.Bank_service.service;

import com.Bank_EffectiveMobile.Bank_service.model.AccountDTO;
import com.Bank_EffectiveMobile.Bank_service.model.AccountDtoConvertor;
import com.Bank_EffectiveMobile.Bank_service.entity.AccountEntity;
import com.Bank_EffectiveMobile.Bank_service.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepository repository;

    public AccountEntity addNewAccount(AccountDTO dto){

        return repository.save(AccountDtoConvertor.convertAccountDtoToEntity(dto));
    }

}
