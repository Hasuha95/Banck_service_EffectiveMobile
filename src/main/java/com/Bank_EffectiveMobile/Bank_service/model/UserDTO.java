package com.Bank_EffectiveMobile.Bank_service.model;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
@ToString
@Data
@Validated
public class UserDTO {
    @Nullable
    @Length(min = 2, max = 16, message = "incorrect name field length")
    private String name;
    @Nullable
    @Length(min = 2, max = 16, message = "incorrect lastName field length")
    private String lastName;
    @Nullable
    @Length(min = 2, max = 16, message = "incorrect surname field length")
    private String surname;
    @Nullable
    private LocalDate dateOfBirth;
    @NotNull
    @Length(min = 4, max = 64, message = "incorrect login field length")
    private String login;
    @NotNull
    @Length(min = 4, max = 64, message = "incorrect password field length")
    private String password;
    @NotNull
    @NumberFormat(pattern = "#-###-###-##-##")
    @Pattern(regexp = "\\d{1}-\\d{3}-\\d{3}-\\d{2}-\\d{2}", message = "Invalid phone number")
    private String number;
    @NotNull
    @Email(message = "Invalid email address")
    private String email;
    @NotNull
    private AccountDTO account;
}
