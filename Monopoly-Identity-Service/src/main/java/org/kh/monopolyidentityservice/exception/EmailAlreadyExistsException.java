package org.kh.monopolyidentityservice.exception;

public class EmailAlreadyExistsException extends BusinessException {

    public EmailAlreadyExistsException(String email) {
        super("Email already exists: " + email, "EMAIL_ALREADY_EXISTS");
    }
}
