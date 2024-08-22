package com.saurabh.social_connect.service.Impl;

import com.saurabh.social_connect.authApi.AuthenticationRequest;
import com.saurabh.social_connect.authApi.AuthenticationResponse;
import com.saurabh.social_connect.authApi.RegisterRequest;
import com.saurabh.social_connect.entity.User;
import com.saurabh.social_connect.enums.Role;
import com.saurabh.social_connect.jwts.JwtUtils;
import com.saurabh.social_connect.repository.UserRepository;
import com.saurabh.social_connect.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthenticationResponse register(RegisterRequest request) {

        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());

        if(userOptional.isPresent()) {
            throw new IllegalArgumentException("Already registered");
        }

            User user = new User();

            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRole(request.getRole()==null ? Role.USER : request.getRole());
            user.setGender(request.getGender());
            userRepository.save(user);
            String jwtToken = jwtUtils.generateToken(user);

            return new AuthenticationResponse(jwtToken);

    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) throws Exception {

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(()-> new IllegalArgumentException("User not found with this email, please register"));


        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getEmail(), request.getPassword()
            ));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

      String token = jwtUtils.generateToken(user);

      return new AuthenticationResponse(token);
    }

//    @Override
//    public String logout() {
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//
//
//    }
}
