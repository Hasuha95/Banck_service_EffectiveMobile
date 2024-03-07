package com.Bank_EffectiveMobile.Bank_service.service;

import com.Bank_EffectiveMobile.Bank_service.model.DAL.AccountDTO;
import com.Bank_EffectiveMobile.Bank_service.model.DAL.AccountDtoConvertor;
import com.Bank_EffectiveMobile.Bank_service.model.entity.AccountEntity;
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
