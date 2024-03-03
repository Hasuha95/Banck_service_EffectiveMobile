package com.Bank_EffectiveMobile.Bank_service.repository;

import java.util.List;

public interface CustomUserRepository {
    List<String> findUsersWithCreatingParameters(String login, String number, String email);
}
