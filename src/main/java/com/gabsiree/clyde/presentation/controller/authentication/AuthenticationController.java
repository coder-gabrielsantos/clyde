package com.gabsiree.clyde.presentation.controller.authentication;

import com.gabsiree.clyde.application.service.authentication.AuthenticationService;
import com.gabsiree.clyde.domain.dto.AuthenticationRequest;
import com.gabsiree.clyde.domain.dto.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody CreateUserRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }
}
