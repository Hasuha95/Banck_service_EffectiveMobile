package com.Bank_EffectiveMobile.Bank_service.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountDTO {
    @NotNull
    @Min(value = 0)
    private float sum;
}
