package com.siddhant.simple.auth.API.service;

import com.siddhant.simple.auth.API.dto.LoginRequest;
import com.siddhant.simple.auth.API.dto.RegisterRequest;
import com.siddhant.simple.auth.API.entity.Role;
import com.siddhant.simple.auth.API.repository.UserRepository;
import com.siddhant.simple.auth.API.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import  com.siddhant.simple.auth.API.entity.User;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public String register(RegisterRequest request){
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new RuntimeException("User already Exists");
        }

        User user=new User();
        user.setEmail(request.getEmail());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);
        return "User registered";
    }

    public String login(LoginRequest request){
        User user=userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid Password");
        }

        return jwtService.generateToken(user);
    }
}
