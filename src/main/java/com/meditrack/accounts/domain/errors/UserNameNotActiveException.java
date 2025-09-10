package com.meditrack.accounts.domain.errors;

public class UserNameNotActiveException extends RuntimeException  {
    public UserNameNotActiveException(String message) {
        super(message);
    }
}