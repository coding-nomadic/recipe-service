package com.recipe.server.service;


import com.recipe.server.entity.User;
import com.recipe.server.models.LoginRequest;
import com.recipe.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    public Boolean isLoginSuccessful(LoginRequest loginRequest) {
        User user = userRepository.findByUserName(loginRequest.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User not found "));
        return loginRequest.getUsername().equals(user.getUserName()) && loginRequest.getPassword().equals(user.getPassword()) ? true : false;
    }
}
