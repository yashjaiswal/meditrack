package com.meditrack.accounts.adapter.in.web;


import com.meditrack.accounts.application.ports.AccountsUseCase;
import com.meditrack.accounts.domain.AccountCreationRequest;
import com.meditrack.accounts.domain.AccountLogInRequest;
import com.meditrack.accounts.domain.LogInResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/v1/accounts")
@AllArgsConstructor
public class AccountsController {

    private final AccountsUseCase accountsUseCase;

    @PostMapping("/signup")
    void createAccount(@RequestBody AccountCreationRequest accountCreationRequest) {
        accountsUseCase.createAccount(accountCreationRequest);
    }

    @PostMapping("/login")
    LogInResponse signIn(@RequestBody AccountLogInRequest accountLogInRequest) {
        LogInResponse logInResponse = accountsUseCase.logIn(accountLogInRequest);

        return logInResponse;
    }
}
