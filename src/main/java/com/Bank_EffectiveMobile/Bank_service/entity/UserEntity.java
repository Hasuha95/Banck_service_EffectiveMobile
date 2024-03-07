package com.Bank_EffectiveMobile.Bank_service.entity;

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
@Table(name = "user")
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
    @Column(length = 64, nullable = false)
    private String password;
    @Column(nullable = false)
    private List<String> numbers = new ArrayList<>();
    @Column(nullable = false)
    private List<String> emails = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    private AccountEntity account;

    public UserEntity() {
        account = new AccountEntity();
    }

    public void setNumbers(List<String> numbers){
        for (String n : numbers) {
            this.numbers.add(n);
        }
    }

    public void setEmails(List<String> emails) {
        for (String e : emails) {
            this.emails.add(e);
        }
    }
}
