package com.example.vaccinationcard.controller;

import com.example.vaccinationcard.configurations.TokenService;
import com.example.vaccinationcard.domain.LoginDTO;
import com.example.vaccinationcard.domain.Token;
import com.example.vaccinationcard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<Token> autenticar(@RequestBody @Valid LoginDTO login) {
        UsernamePasswordAuthenticationToken loginData = login.converter();
        Authentication authentication = authenticationManager.authenticate(loginData);
        String token = tokenService.generateToken(authentication);
        return ResponseEntity.ok(new Token(token, "Bearer"));
    }

    @PostMapping("/create")
    public ResponseEntity<LoginDTO> createUser(@RequestBody @Valid LoginDTO user) {
        return ResponseEntity.ok(service.save(user));
    }

}
