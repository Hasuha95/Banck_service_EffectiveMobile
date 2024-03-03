package com.Bank_EffectiveMobile.Bank_service.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@EqualsAndHashCode
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 16)
    private String name;
    @Column(length = 16)
    private String lastName;
    @Column(length = 16)
    private String surname;
    private LocalDate dateOfBirth;
    @Column(length = 64, nullable = false, unique = true)
    private String login;
    @Column(length = 64, nullable = false, unique = true)
    private String password;
    @Column(nullable = false)
    private List<String> listOfNumbers = new ArrayList<>();
    @Column(nullable = false)
    private List<String> listOfEmails = new ArrayList<>();
    @OneToOne
    private BankAccountEntity account;

    public UserEntity() {
//        account = new BankAccountEntity();
    }

    public void setListOfNumbers(String number){
        listOfNumbers.add(number);
    }

    public void setListOfEmails(String email) {
        listOfEmails.add(email);
    }
}
