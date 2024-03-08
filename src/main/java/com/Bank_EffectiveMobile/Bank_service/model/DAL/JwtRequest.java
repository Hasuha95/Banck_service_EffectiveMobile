package com.Bank_EffectiveMobile.Bank_service.model.DAL;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class JwtRequest {
    private String username;
    private String password;
}
