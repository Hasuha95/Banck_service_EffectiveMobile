package com.Bank_EffectiveMobile.Bank_service.repository.RepositoryImpl;

import com.Bank_EffectiveMobile.Bank_service.repository.CustomUserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

public class CustomUserRepositoryImpl implements CustomUserRepository{
    private Query query;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<String> findUsersWithCreatingParameters(String login, String number, String email) {
        query = entityManager.createQuery("SELECT u.login, u.number, u.email FROM BankUser u " +
                "WHERE u.login =:login OR u.number LIKE :number OR u.email LIKE :email");
        query.setParameter("login", login);
        query.setParameter("number", "%" + number + "%");
        query.setParameter("login", "%" + email + "%");

        return query.getResultList();
    }
}
