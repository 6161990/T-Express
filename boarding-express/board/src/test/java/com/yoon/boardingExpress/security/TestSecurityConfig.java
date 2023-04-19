package com.yoon.boardingExpress.security;

import com.yoon.boardingExpress.config.SecurityConfig;
import com.yoon.boardingExpress.domain.UserAccount;
import com.yoon.boardingExpress.repository.UserAccountRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@Import(SecurityConfig.class)
public class TestSecurityConfig {

    @MockBean
    private UserAccountRepository repository;

    @BeforeTestMethod
    public void securitySetUp(){
        given(repository.findById(anyString())).willReturn(Optional.of(UserAccount.of(
                "userId",
                "password",
                "yoon@gmail.co.kr",
                "yoonji",
                "01049492929",
                "memo"
        )));
    }

}
