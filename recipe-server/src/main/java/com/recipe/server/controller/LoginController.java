//package com.recipe.server.controller;
//
//
//import com.recipe.server.models.RegistrationRequest;
//import com.recipe.server.service.RegistrationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//
//@RestController
//@RequestMapping(path = "/api/v1/signup")
//public class LoginController {
//
//    @Autowired
//    RegistrationService registrationService;
//
//    @PostMapping
//    public ResponseEntity<Void> signUp(@RequestBody RegistrationRequest registrationRequest) {
//        registrationService.registerUser(registrationRequest);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//
//    @GetMapping(path = "/confirm")
//    public String confirm(@RequestParam("token") String token) {
//        return registrationService.confirmToken(token);
//    }
//}