package com.Bank_EffectiveMobile.Bank_service.repository.RepositoryImpl;

import com.Bank_EffectiveMobile.Bank_service.repository.CustomUserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CustomUserRepositoryImpl implements CustomUserRepository{
    private Query query;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<String> findUsersWithCreatingParameters(String login, String number, String email) {
        query = entityManager.createQuery("SELECT u.login, u.numbers, u.emails FROM UserEntity u " +
                "WHERE u.login =:login OR str(u.numbers) LIKE :number OR str(u.emails) LIKE :email");
        query.setParameter("login", login);
        query.setParameter("number", "%" + number + "%");
        query.setParameter("email", "%" + email + "%");

        return query.getResultList();
    }
}
