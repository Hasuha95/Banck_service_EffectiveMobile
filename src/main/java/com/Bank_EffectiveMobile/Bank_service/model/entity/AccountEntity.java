package com.Bank_EffectiveMobile.Bank_service.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
@ToString
@Data
@NoArgsConstructor
@Entity
@Table(name = "account")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Value(value = "${bank-account.default-sum}")
    private float sum;

    public AccountEntity(float sum) {
        this.sum = sum;
    }
}
