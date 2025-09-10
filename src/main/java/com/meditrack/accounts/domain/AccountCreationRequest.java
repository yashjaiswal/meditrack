package com.meditrack.accounts.domain;

import lombok.Data;

@Data
public class AccountCreationRequest {

    private String userName;
    private String password;
    private String email;
    private Role role;
    private String fullName;
}
