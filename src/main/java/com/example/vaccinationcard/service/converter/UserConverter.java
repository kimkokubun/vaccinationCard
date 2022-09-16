package com.example.vaccinationcard.service.converter;

import com.example.vaccinationcard.domain.DocumentDTO;
import com.example.vaccinationcard.domain.LoginDTO;
import com.example.vaccinationcard.models.Document;
import com.example.vaccinationcard.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public LoginDTO convertEntityToDTO(User document){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(document, LoginDTO.class);
    }
    public User convertDTOtoEntity(LoginDTO documentDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(documentDTO, User.class);
    }
}
