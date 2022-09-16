package com.example.vaccinationcard.service;

import com.example.vaccinationcard.domain.LoginDTO;
import com.example.vaccinationcard.domain.RoleDTO;
import com.example.vaccinationcard.models.Role;
import com.example.vaccinationcard.models.User;
import com.example.vaccinationcard.models.UserRole;
import com.example.vaccinationcard.repository.RoleRepository;
import com.example.vaccinationcard.repository.UserRepository;
import com.example.vaccinationcard.service.converter.UserConverter;
import net.bytebuddy.utility.nullability.AlwaysNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserConverter userConverter;

    @Transactional
    public LoginDTO save(LoginDTO user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Optional<Role> role = roleRepository.findByName(UserRole.USER.name());
        if(role.isPresent()){
            user.setRoles(Collections.singleton(new RoleDTO(role.get().getId(),role.get().getName())));
            User user1 = userConverter.convertDTOtoEntity(user);
            User userDB = this.userRepository.save(user1);
            return userConverter.convertEntityToDTO(userDB);
        }
        return null;
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
