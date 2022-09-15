package com.example.vaccinationcard.service;

import com.example.vaccinationcard.models.User;
import com.example.vaccinationcard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public Long saveDto(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return this.userRepository.save(new User(user)).getId();
    }

    public Optional<User> findUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public Optional<User> findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Optional<User> findUserById(Long id){
        return userRepository.findById(id);
    }
}
