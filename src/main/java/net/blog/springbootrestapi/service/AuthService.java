package net.blog.springbootrestapi.service;

import net.blog.springbootrestapi.dto.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}
