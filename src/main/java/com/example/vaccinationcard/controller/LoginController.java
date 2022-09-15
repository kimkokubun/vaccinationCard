package com.example.vaccinationcard.controller;

import com.example.vaccinationcard.configurations.TokenService;
import com.example.vaccinationcard.domain.Login;
import com.example.vaccinationcard.domain.Token;
import com.example.vaccinationcard.domain.UserInfos;
import com.example.vaccinationcard.models.User;
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
    public ResponseEntity<Token> autenticar(@RequestBody @Valid Login login) {
        UsernamePasswordAuthenticationToken loginData = login.converter();

        try {
            Authentication authentication = authenticationManager.authenticate(loginData);
            String token = tokenService.generateToken(authentication);
            return ResponseEntity.ok(new Token(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Long> createUser(@RequestBody @Valid UserInfos user){
        User userDTO = new User(user.getUsername(),user.getEmail(), user.getPassword());
        return ResponseEntity.ok(service.saveDto(userDTO));
    }

}
