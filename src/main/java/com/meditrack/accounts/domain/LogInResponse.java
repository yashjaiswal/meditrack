package com.meditrack.accounts.domain;

import lombok.Data;

@Data
public class LogInResponse {
    String token;
    String userName;
    Long userId;

}
