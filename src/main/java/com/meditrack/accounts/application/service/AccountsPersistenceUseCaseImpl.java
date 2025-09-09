package com.meditrack.accounts.application.service;

import com.meditrack.accounts.adapter.out.db.entity.AccountsEntity;
import com.meditrack.accounts.adapter.out.db.repo.AccountsRepository;
import com.meditrack.accounts.application.ports.AccountsPersistenceUseCase;
import com.meditrack.accounts.domain.AccountCreationRequest;
import com.meditrack.accounts.domain.UserAccount;
import com.meditrack.accounts.domain.errors.AccountAlreadyExistsException;
import com.meditrack.accounts.domain.errors.UserNameNotActiveException;
import com.meditrack.accounts.domain.errors.UserNameNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountsPersistenceUseCaseImpl implements AccountsPersistenceUseCase {

    private final AccountsRepository accountsRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void storeNewAccount(AccountCreationRequest accountCreationRequest) {
        validateRequest(accountCreationRequest);

        AccountsEntity accountsEntity = new AccountsEntity();

        accountsEntity.setUserName(accountCreationRequest.getUserName().toLowerCase());
        accountsEntity.setFullName(accountCreationRequest.getFullName());
        accountsEntity.setRole(accountCreationRequest.getRole());
        accountsEntity.setEmail(accountCreationRequest.getEmail());
        accountsEntity.setPassword(bCryptPasswordEncoder.encode(accountCreationRequest.getPassword()));
        accountsEntity.setIsActive(true);

        accountsRepository.save(accountsEntity);

    }

    @Override
    public UserAccount fetchAccount(String userName) {
        AccountsEntity accountsEntity = accountsRepository.findByUserNameIgnoreCase(userName);
        if (Objects.isNull(accountsEntity)) {
            log.error("Username does not exist. userName={}", userName);
            throw new UserNameNotFoundException("Username does not exist");
        }

        if (Boolean.FALSE.equals(accountsEntity.getIsActive())) {
            log.error("Username exists but not active. userName={}", userName);
            throw new UserNameNotActiveException("Username not active");
        }

        UserAccount userAccount = new UserAccount();
        userAccount.setUserName(accountsEntity.getUserName());
        userAccount.setPassword(accountsEntity.getPassword());
        userAccount.setIsActive(accountsEntity.getIsActive());
        userAccount.setUserId(accountsEntity.getId());

        return userAccount;
    }

    private void validateRequest(AccountCreationRequest accountCreationRequest) {
        if (accountsRepository.existsByUserName(accountCreationRequest.getUserName())) {
            log.error("Account already exists with username. userName={} email={}", accountCreationRequest.getUserName(), accountCreationRequest.getEmail());
            throw new AccountAlreadyExistsException("Username already exists");
        }

        if(accountsRepository.existsByEmail(accountCreationRequest.getEmail())) {
            log.error("Account already exists with email. userName={} email={}", accountCreationRequest.getUserName(), accountCreationRequest.getEmail());
            throw new AccountAlreadyExistsException("Email already exists");
        }
    }
}
