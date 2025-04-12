package com.gabsiree.clyde.application.service.authentication;

import com.gabsiree.clyde.domain.dto.AuthenticationRequest;
import com.gabsiree.clyde.domain.dto.AuthenticationResponse;
import com.gabsiree.clyde.domain.dto.CreateUserRequest;
import com.gabsiree.clyde.domain.model.User;
import com.gabsiree.clyde.domain.repository.authentication.UserRepository;
import com.gabsiree.clyde.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authManager;

    public AuthenticationResponse login(AuthenticationRequest request) {
        var auth = new UsernamePasswordAuthenticationToken(
                request.getEmail(), request.getPassword()
        );
        authManager.authenticate(auth);

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtUtil.generateToken(user);
        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse register(CreateUserRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        userRepository.save(user);

        String token = jwtUtil.generateToken(user);
        return new AuthenticationResponse(token);
    }
}
