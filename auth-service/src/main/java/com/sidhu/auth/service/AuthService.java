package com.sidhu.auth.service;

import com.sidhu.auth.model.AuthRequest;
import com.sidhu.auth.model.AuthResponse;
import com.sidhu.auth.model.User;
import org.apache.http.auth.InvalidCredentialsException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final JwtUtil jwtUtil;
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    public AuthService(final JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public AuthResponse authenticate(AuthRequest authRequest) throws InvalidCredentialsException {
        authRequest.setPassword(BCrypt.hashpw(authRequest.getPassword(), BCrypt.gensalt()));
        User userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());

        if (userDetails == null || !BCrypt.checkpw(userDetails.getPassword(), authRequest.getPassword())) {
            throw new InvalidCredentialsException("Invalid Credentials!!!");
        }
        String accessToken = jwtUtil.generate(authRequest, "ACCESS");
        String refreshToken = jwtUtil.generate(authRequest, "REFRESH");

        return new AuthResponse(accessToken, refreshToken);
    }
}
