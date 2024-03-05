package com.Bank_EffectiveMobile.Bank_service.model;

import com.Bank_EffectiveMobile.Bank_service.exception.BadRequestParametersException;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class FilterParameters {
    private LocalDate date;
    private FoolName foolName;
    private String number;
    private String email;
    private TypeOfFilter typeOfFilter;

    public FilterParameters(String date,
                            String number,
                            String name,
                            String lastName,
                            String surname,
                            String email)
    {
        typeOfFilter = TypeOfFilter.DEFAULT;
        setDate(date);
        setNumber(number);
        setFoolName(name, lastName, surname);
        setEmail(email);
    }

    public void setDate(String date) {
        Pattern datePattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        Matcher dateMatcher = datePattern.matcher(date);
        if (dateMatcher.matches()){
            this.date = LocalDate.parse(date);
            typeOfFilter = TypeOfFilter.DATE;
        } else {
            throw new BadRequestParametersException("invalid date filter parameter");
        }
    }

    public void setFoolName(String name, String lastName, String surname) {
        if (name == null || lastName == null || surname == null){
            throw new BadRequestParametersException("invalid foolName filter parameter");
        } else {
            this.foolName = new FoolName(name, lastName, surname);
            typeOfFilter = TypeOfFilter.FOOL_NAME;
        }
    }

    public void setNumber(String number) {
        Pattern datePattern = Pattern.compile("\\d{1}-\\d{3}-\\d{3}-\\d{2}-\\d{2}");
        Matcher dateMatcher = datePattern.matcher(number);
        if (dateMatcher.matches()){
            this.number = number;
            typeOfFilter = TypeOfFilter.NUMBER;
        } else {
            throw new BadRequestParametersException("invalid number filter parameter");
        }
    }

    public void setEmail(String email) {
        if (email.split("@").length <= 1) {
            throw new BadRequestParametersException("invalid mail filter parameter");
        } else {
            typeOfFilter = TypeOfFilter.EMAIL;
            this.email = email;
        }
    }

    @Data
    @AllArgsConstructor
    private class FoolName{
        String name;
        String lastName;
        String surname;
    }

    private enum TypeOfFilter{
        DEFAULT,
        DATE,
        NUMBER,
        FOOL_NAME,
        EMAIL
    }
}
