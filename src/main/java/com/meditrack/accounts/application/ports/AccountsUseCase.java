package com.meditrack.accounts.application.ports;

import com.meditrack.accounts.domain.AccountCreationRequest;
import com.meditrack.accounts.domain.AccountLogInRequest;
import com.meditrack.accounts.domain.LogInResponse;

public interface AccountsUseCase {

    void createAccount(AccountCreationRequest accountCreationRequest);

    LogInResponse logIn(AccountLogInRequest accountLogInRequest);
}
