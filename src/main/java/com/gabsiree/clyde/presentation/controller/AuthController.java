package com.gabsiree.clyde.presentation.controller;

import com.gabsiree.clyde.domain.dto.AuthenticationRequest;
import com.gabsiree.clyde.domain.dto.AuthenticationResponse;
import com.gabsiree.clyde.domain.dto.RegisterRequest;
import com.gabsiree.clyde.domain.model.User;
import com.gabsiree.clyde.domain.repository.UserRepository;
import com.gabsiree.clyde.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already in use");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail());
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {
        var authToken = new UsernamePasswordAuthenticationToken(
                request.getEmail(), request.getPassword()
        );
        authenticationManager.authenticate(authToken);

        String token = jwtUtil.generateToken(request.getEmail());
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }
}
