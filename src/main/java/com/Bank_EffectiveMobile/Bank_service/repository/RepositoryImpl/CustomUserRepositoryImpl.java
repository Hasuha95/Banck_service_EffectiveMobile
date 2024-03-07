package com.Bank_EffectiveMobile.Bank_service.repository.RepositoryImpl;

import com.Bank_EffectiveMobile.Bank_service.model.entity.UserEntity;
import com.Bank_EffectiveMobile.Bank_service.repository.CustomUserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class CustomUserRepositoryImpl implements CustomUserRepository{
    private Query query;
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<String> isUserExist(String login, String number, String email) {
        query = entityManager.createQuery("SELECT u.login, u.numbers, u.emails FROM UserEntity u " +
                "WHERE u.login =:login OR str(u.numbers) LIKE :number OR str(u.emails) LIKE :email");
        query.setParameter("login", login);
        query.setParameter("number", "%" + number + "%");
        query.setParameter("email", "%" + email + "%");

        return query.getResultList();
    }

    @Override
    public List<String> canBeUpdate(String login, List<String> numbers, List<String> emails) {
        query = entityManager.createQuery("SELECT u.login, u.numbers, u.emails FROM UserEntity u" +
                " WHERE u.login != :login" +
                " AND (u.numbers IN (:numbers) OR u.emails IN (:emails))");

        query.setParameter("login", login);
        query.setParameter("numbers", numbers);
        query.setParameter("emails", emails);

        return query.getResultList();
    }

    @Override
    public List<UserEntity> findUserByDate(LocalDate date){
        query = entityManager.createQuery("FROM UserEntity u " +
                "WHERE u.dateOfBirth >= :date");
        query.setParameter("date", date);
        return query.getResultList();
    }

    @Override
    public List<UserEntity> findUserByNumber(String number) {
        query = entityManager.createQuery("FROM UserEntity u " +
                "WHERE str(u.numbers) LIKE :number");
        query.setParameter("number", "%" + number + "%");
        return query.getResultList();
    }

    @Override
    public List<UserEntity> findUserByFullName(String name, String lastName, String surname){
        query = entityManager.createQuery("FROM UserEntity u " +
                "WHERE u.name = :name AND u.lastName = :lastName AND u.surname = :surname");
        query.setParameter("name", name);
        query.setParameter("lastName", lastName);
        query.setParameter("surname", surname);

        return query.getResultList();
    }

    @Override
    public List<UserEntity> findUserByEmail(String email) {
        query = entityManager.createQuery("FROM UserEntity u " +
                "WHERE str(u.emails) LIKE :email");
        query.setParameter("email", "%" + email + "%");
        return query.getResultList();
    }


    @Deprecated
    @Override
    public UserEntity findUserWithAnyNumber(List<String> numbers) {
        query = entityManager.createQuery("FROM UserEntity u WHERE u.numbers && " +
                "ARRAY[:numbers]::character varying[]");
        StringBuilder builder = new StringBuilder();
        int index = 0;
        //   [0,1,2]
        while (!numbers.isEmpty()){
            builder.append(numbers.get(index));
            numbers.remove(index);
            index++;
        }
        query.setParameter("numbers", "");

        return null;
    }

}
