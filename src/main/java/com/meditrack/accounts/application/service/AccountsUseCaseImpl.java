package com.meditrack.accounts.application.service;

import com.meditrack.accounts.application.ports.AccountsPersistenceUseCase;
import com.meditrack.accounts.application.ports.AccountsUseCase;
import com.meditrack.accounts.domain.AccountCreationRequest;
import com.meditrack.accounts.domain.AccountLogInRequest;
import com.meditrack.accounts.domain.LogInResponse;
import com.meditrack.accounts.domain.UserAccount;
import com.meditrack.accounts.domain.errors.InvalidPasswordException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountsUseCaseImpl implements AccountsUseCase {

    private final AccountsPersistenceUseCase accountsPersistenceUseCase;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokensUtil tokensUtil;

    @Override
    public void createAccount(AccountCreationRequest accountCreationRequest) {
        accountsPersistenceUseCase.storeNewAccount(accountCreationRequest);
    }

    @Override
    public LogInResponse logIn(AccountLogInRequest accountLogInRequest) {
        UserAccount userAccount = accountsPersistenceUseCase.fetchAccount(accountLogInRequest.getUserName());
        if (!bCryptPasswordEncoder.matches(accountLogInRequest.getPassword(), userAccount.getPassword())) {
            log.error("Invalid password. userName={}", accountLogInRequest.getUserName());
            throw new InvalidPasswordException("Invalid password");
        }

        String token = tokensUtil.generateToken(userAccount);
        LogInResponse logInResponse = new LogInResponse();
        logInResponse.setToken(token);
        logInResponse.setUserName(userAccount.getUserName());
        logInResponse.setUserId(userAccount.getUserId());

        return logInResponse;
    }
}
