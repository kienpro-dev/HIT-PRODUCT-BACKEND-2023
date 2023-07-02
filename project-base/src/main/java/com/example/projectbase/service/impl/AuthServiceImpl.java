package com.example.projectbase.service.impl;

import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.constant.RoleConstant;
import com.example.projectbase.domain.dto.CustomerDto;
import com.example.projectbase.domain.dto.request.LoginRequestDto;
import com.example.projectbase.domain.dto.request.RegisterRequestDto;
import com.example.projectbase.domain.dto.request.TokenRefreshRequestDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.dto.response.LoginResponseDto;
import com.example.projectbase.domain.dto.response.TokenRefreshResponseDto;
import com.example.projectbase.domain.entity.Customer;
import com.example.projectbase.domain.entity.User;
import com.example.projectbase.domain.mapper.UserMapper;
import com.example.projectbase.exception.DataIntegrityViolationException;
import com.example.projectbase.exception.InvalidException;
import com.example.projectbase.exception.UnauthorizedException;
import com.example.projectbase.repository.RoleRepository;
import com.example.projectbase.repository.UserRepository;
import com.example.projectbase.security.UserPrincipal;
import com.example.projectbase.security.jwt.JwtTokenProvider;
import com.example.projectbase.service.AuthService;
import com.example.projectbase.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final AuthenticationManager authenticationManager;

  private final JwtTokenProvider jwtTokenProvider;

  private final UserRepository userRepository;

  private final RoleRepository roleRepository;

  private final UserMapper userMapper;

  private final PasswordEncoder passwordEncoder;

  private final CustomerService customerService;

  @Override
  public LoginResponseDto login(LoginRequestDto request) {
    try {
      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
      SecurityContextHolder.getContext().setAuthentication(authentication);
      UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
      String accessToken = jwtTokenProvider.generateToken(userPrincipal, Boolean.FALSE);
      String refreshToken = jwtTokenProvider.generateToken(userPrincipal, Boolean.TRUE);
      return new LoginResponseDto(accessToken, refreshToken, userPrincipal.getId(), authentication.getAuthorities());
    } catch (InternalAuthenticationServiceException e) {
      throw new UnauthorizedException(ErrorMessage.Auth.ERR_INCORRECT_USERNAME);
    } catch (BadCredentialsException e) {
      throw new UnauthorizedException(ErrorMessage.Auth.ERR_INCORRECT_PASSWORD);
    }
  }

  @Override
  public TokenRefreshResponseDto refresh(TokenRefreshRequestDto request) {
    return null;
  }

  @Override
  public CommonResponseDto logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
    SecurityContextLogoutHandler logout = new SecurityContextLogoutHandler();
    logout.logout(request, response, authentication);
    return new CommonResponseDto(true, "Logout successfully");
  }

  @Override
  public User register(RegisterRequestDto requestDto) {
    boolean isUsernameExists = userRepository.existsByUsername(requestDto.getUsername());
    boolean isEmailExists = userRepository.existsByEmail(requestDto.getEmail());

    if(isUsernameExists && isEmailExists) {
      throw new DataIntegrityViolationException(ErrorMessage.Auth.ERR_DUPLICATE_USERNAME_EMAIL);
    } else if (isUsernameExists) {
      throw new DataIntegrityViolationException(ErrorMessage.Auth.ERR_DUPLICATE_USERNAME);
    } else if (isEmailExists) {
      throw new DataIntegrityViolationException(ErrorMessage.Auth.ERR_DUPLICATE_EMAIL);
    } else {
      if(!requestDto.getPassword().equals(requestDto.getRepeatPassword())) {
        throw new InvalidException(ErrorMessage.INVALID_REPEAT_PASSWORD);
      } else {
        User user = userMapper.toUser(requestDto);
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        user.setRole(roleRepository.findByRoleName(RoleConstant.USER));
        user.setCustomer(customerService.createCustomer(new CustomerDto(user.getUsername(), null, null, null)));
        return userRepository.save(user);
      }
    }
  }

}
