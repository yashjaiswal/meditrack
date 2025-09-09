package com.meditrack.accounts.application.ports;

import com.meditrack.accounts.domain.AccountCreationRequest;
import com.meditrack.accounts.domain.UserAccount;

public interface AccountsPersistenceUseCase {

    void storeNewAccount(AccountCreationRequest accountCreationRequest);

    UserAccount fetchAccount(String userName);

}
