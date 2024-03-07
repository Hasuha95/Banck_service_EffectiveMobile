package com.Bank_EffectiveMobile.Bank_service.model.DAL;

import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class AccountDTO {

    @Min(value = 0)
    private float sum;
}
