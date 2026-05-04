package com.siddhant.simple.auth.API.controller;
import com.siddhant.simple.auth.API.dto.ChangePasswordRequest;
import com.siddhant.simple.auth.API.dto.UpdateRequest;
import com.siddhant.simple.auth.API.entity.User;
import com.siddhant.simple.auth.API.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TestController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/test")
    public String test(){
        return "Protected API working";
    }

    @GetMapping("/profile")
    public User getProfile(Principal principal) {
        return userRepository.findByEmail(principal.getName())
                .orElseThrow();

    }

    @PutMapping("/profile")
    public String updateProfile(@RequestBody UpdateRequest request, Principal principal){
        String email=principal.getName();

        User user=userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setEmail(request.getEmail());
        userRepository.save(user);

        return "Profile updated";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestBody ChangePasswordRequest req,
                                 Principal principal) {

        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow();

        if(!passwordEncoder.matches(req.getOldPassword(), user.getPassword())){
            throw new RuntimeException("Wrong old password");
        }

        user.setPassword(passwordEncoder.encode(req.getNewPassword()));
        userRepository.save(user);

        return "Password updated ";
    }
}
