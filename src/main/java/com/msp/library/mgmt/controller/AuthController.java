package com.msp.library.mgmt.controller;

import com.msp.library.mgmt.model.User;
import com.msp.library.mgmt.service.UserService;
import com.msp.library.mgmt.utilities.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @RequestMapping("/home")
    public String home() {
        return "Welcome home page";
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        // Save password using BCryptPasswordEncoder
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setName(user.getName());
        User registeredUser = userService.save(user);
        if(registeredUser == null) {
            log.error("Unable to create user: {}", user);
            return ResponseEntity.badRequest().build();
        }
        log.info("New user created: {}", registeredUser);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        if(authentication == null) {
            log.error("Invalid username or password: {}", loginRequest);
            return ResponseEntity.badRequest().build();
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Map<String, Object> claims = new HashMap<>();
        // Generate JWT token
        String token = jwtUtil.generateToken(loginRequest.getUsername(), claims);

        return ResponseEntity.ok(token);
    }

}

class LoginRequest {
    private String username;
    private String password;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
