package com.sidhu.auth.service;


import com.sidhu.auth.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService {

    public User loadUserByUsername(String username) {
        if (username.equals("user"))
            return new User("user", "password", new ArrayList<>());
        return null;
    }
}
