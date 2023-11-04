package net.blog.springbootrestapi.controller;

import net.blog.springbootrestapi.dto.JWTAuthResponseDto;
import net.blog.springbootrestapi.dto.LoginDto;
import net.blog.springbootrestapi.dto.RegisterDto;
import net.blog.springbootrestapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthService authService;
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponseDto> login(@RequestBody LoginDto loginDto) {
        String token = authService.login(loginDto);
        JWTAuthResponseDto jwtAuthResponseDto = new JWTAuthResponseDto();
        jwtAuthResponseDto.setAccessToken(token);
        return ResponseEntity.ok(jwtAuthResponseDto);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
