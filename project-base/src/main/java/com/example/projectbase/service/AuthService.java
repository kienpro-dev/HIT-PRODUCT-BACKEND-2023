package com.example.projectbase.service;

import com.example.projectbase.domain.dto.request.LoginRequestDto;
import com.example.projectbase.domain.dto.request.RegisterRequestDto;
import com.example.projectbase.domain.dto.request.TokenRefreshRequestDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.dto.response.LoginResponseDto;
import com.example.projectbase.domain.dto.response.TokenRefreshResponseDto;
import com.example.projectbase.domain.entity.User;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthService {

  LoginResponseDto login(LoginRequestDto request);

  TokenRefreshResponseDto refresh(TokenRefreshRequestDto request);

  CommonResponseDto logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication);

  User register(RegisterRequestDto requestDto);


}
