package net.blog.springbootrestapi.service;

import net.blog.springbootrestapi.dto.LoginDto;
import net.blog.springbootrestapi.dto.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
