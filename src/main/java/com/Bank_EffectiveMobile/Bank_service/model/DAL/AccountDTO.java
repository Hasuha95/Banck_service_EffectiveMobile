package com.Bank_EffectiveMobile.Bank_service.model.DAL;

import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

@Data
@ToString
public class AccountDTO {
    @Value("${account.default-sum}")
    private float defaultSum;
    @Min(value = 0)
    private float sum;
}
