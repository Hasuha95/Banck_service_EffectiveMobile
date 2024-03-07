package com.Bank_EffectiveMobile.Bank_service.model.DAL;

import com.Bank_EffectiveMobile.Bank_service.exception.BadRequestParametersException;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import jakarta.validation.constraints.Pattern;
import lombok.ToString;

@ToString
@Data
public class FilterParameters {
    private LocalDate date;
    private String name;
    private String lastName;
    private String surname;
    @Pattern(regexp = "\\d{1}-\\d{3}-\\d{3}-\\d{2}-\\d{2}",
            message = "Invalid phone number")
    private String number;
    @Email(message = "Invalid email address")
    private String email;
}