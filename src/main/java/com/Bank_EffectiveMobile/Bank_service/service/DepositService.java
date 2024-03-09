package com.Bank_EffectiveMobile.Bank_service.service;

import com.Bank_EffectiveMobile.Bank_service.model.entity.AccountEntity;
import com.Bank_EffectiveMobile.Bank_service.model.entity.UserEntity;
import com.Bank_EffectiveMobile.Bank_service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
@Slf4j
@Service
public class DepositService {
    @Autowired
    private UserRepository repository;

    @Transactional
    @Scheduled(cron = "* * * * * *")
    private void autoIncreaseInContribution(){
        UserEntity user = repository.findUserEntityByLogin("here must be Username");
        log.info("user: " + user.getLogin());
        AccountEntity account = user.getAccount();
        account.setSum(account.getSum()*1.2f);
        log.info("set sum: " + account.getSum());
        user.setAccount(account);
        repository.save(user);
        log.info("OK");
    }

}
