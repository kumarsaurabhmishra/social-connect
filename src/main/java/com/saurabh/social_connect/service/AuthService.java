package com.saurabh.social_connect.service;

import com.saurabh.social_connect.authApi.AuthenticationRequest;
import com.saurabh.social_connect.authApi.AuthenticationResponse;
import com.saurabh.social_connect.authApi.RegisterRequest;

public interface AuthService {

    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request) throws Exception;

//    String logout();
}
