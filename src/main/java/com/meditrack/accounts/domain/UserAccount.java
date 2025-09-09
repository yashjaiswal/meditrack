package com.meditrack.accounts.domain;

import lombok.Data;

@Data
public class UserAccount {
    private Long userId;
    private String userName;
    private String password;
    private Boolean isActive;
}
