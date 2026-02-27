package org.kh.monopolyidentityservice.service;

import lombok.RequiredArgsConstructor;
import org.kh.monopolyidentityservice.dto.forgotPassword.ForgotPasswordRequestDto;
import org.kh.monopolyidentityservice.dto.forgotPassword.ForgotPasswordResponseDto;
import org.kh.monopolyidentityservice.dto.login.LoginRequestDto;
import org.kh.monopolyidentityservice.dto.login.LoginResponseDto;
import org.kh.monopolyidentityservice.dto.register.RegisterRequestDto;
import org.kh.monopolyidentityservice.dto.register.RegisterResponseDto;
import org.kh.monopolyidentityservice.exception.EmailAlreadyExistsException;
import org.kh.monopolyidentityservice.repository.UsersRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final UsersRepository usersRepository;

    @Override
    public RegisterResponseDto register(RegisterRequestDto request) {

        if (usersRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }



        return null;
    }

    @Override
    public LoginResponseDto login(LoginRequestDto request) {
        return null;
    }

    @Override
    public ForgotPasswordResponseDto forgotPassword(ForgotPasswordRequestDto request) {
        return null;
    }
}
