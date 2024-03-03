package com.Bank_EffectiveMobile.Bank_service.model.DTO;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@ToString
@Data
public class UserDTO {
    private String name;
    private String lastName;
    private String surname;
    private LocalDate dateOfBirth;
    @NotNull
    @Min(4)
    @Max(64)
    private String login;
    @NotNull
    @Min(4)
    @Max(64)
    private String password;
    @NotNull
    @NumberFormat(pattern = "#-###-###-##-##")
    private String number;
    @NotNull
    @Email
    private String email;
    private float startSum;
}
