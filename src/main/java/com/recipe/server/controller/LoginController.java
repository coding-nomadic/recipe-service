package com.recipe.server.controller;


import com.recipe.server.models.LoginRequest;
import com.recipe.server.service.LoginService;
import com.recipe.server.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api")
public class LoginController {

    @Autowired
    RegistrationService registrationService;

    @Autowired
    private LoginService loginService;

    @GetMapping(path="/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("Logged out successfully");
    }
    @PostMapping(path="/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        if (loginService.isLoginSuccessful(loginRequest)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
