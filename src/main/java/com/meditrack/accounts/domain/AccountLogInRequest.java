package com.meditrack.accounts.domain;

import lombok.Data;

@Data
public class AccountLogInRequest {

    private String userName;
    private String password;
}
