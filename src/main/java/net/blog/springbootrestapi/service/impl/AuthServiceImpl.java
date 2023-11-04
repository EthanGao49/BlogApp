package net.blog.springbootrestapi.service.impl;

import net.blog.springbootrestapi.dto.LoginDto;
import net.blog.springbootrestapi.dto.RegisterDto;
import net.blog.springbootrestapi.entity.Role;
import net.blog.springbootrestapi.entity.User;
import net.blog.springbootrestapi.exception.BlogAPIException;
import net.blog.springbootrestapi.repository.RoleRepository;
import net.blog.springbootrestapi.repository.UserRepository;
import net.blog.springbootrestapi.secucrity.JwtAuthenticationFilter;
import net.blog.springbootrestapi.secucrity.JwtTokenProvider;
import net.blog.springbootrestapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication =  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginDto.getEmailOrUsername(),
                    loginDto.getPassword() ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtTokenProvider.generateToken(authentication);
    }

    @Override
    public String register(RegisterDto registerDto) {
        // Existence check
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Email exists already.");
        }
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Username exists already.");
        }

        User user = new User();
        user.setEmail(registerDto.getEmail());
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setName(registerDto.getName());

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        userRepository.save(user);

        return "Registered successfully.";
    }
}
