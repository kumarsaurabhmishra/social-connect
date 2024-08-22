package com.saurabh.social_connect.controller;

import com.saurabh.social_connect.authApi.AuthenticationRequest;
import com.saurabh.social_connect.authApi.RegisterRequest;
import com.saurabh.social_connect.authApi.AuthenticationResponse;
import com.saurabh.social_connect.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/signup")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @GetMapping("/login")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request) throws Exception {

        return ResponseEntity.ok(authService.authenticate(request));
    }

//    @GetMapping("/logout")
//    public ResponseEntity<String> logout() {
//        return ResponseEntity.ok(authService.logout());
//    }

}
