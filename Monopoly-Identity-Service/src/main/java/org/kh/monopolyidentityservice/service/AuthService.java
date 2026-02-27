package org.kh.monopolyidentityservice.service;

import org.kh.monopolyidentityservice.dto.forgotPassword.ForgotPasswordRequestDto;
import org.kh.monopolyidentityservice.dto.forgotPassword.ForgotPasswordResponseDto;
import org.kh.monopolyidentityservice.dto.login.LoginRequestDto;
import org.kh.monopolyidentityservice.dto.login.LoginResponseDto;
import org.kh.monopolyidentityservice.dto.register.RegisterRequestDto;
import org.kh.monopolyidentityservice.dto.register.RegisterResponseDto;

public interface AuthService {
    RegisterResponseDto register(RegisterRequestDto request);
    LoginResponseDto login(LoginRequestDto request);
    ForgotPasswordResponseDto forgotPassword(ForgotPasswordRequestDto request);
}
