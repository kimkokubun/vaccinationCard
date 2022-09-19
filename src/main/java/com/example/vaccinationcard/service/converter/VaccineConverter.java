package com.example.vaccinationcard.service.converter;

import com.example.vaccinationcard.domain.LoginDTO;
import com.example.vaccinationcard.domain.VaccineDTO;
import com.example.vaccinationcard.models.User;
import com.example.vaccinationcard.models.Vaccine;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component

public class VaccineConverter {

    public VaccineDTO convertEntityToDTO(Vaccine document){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(document, VaccineDTO.class);
    }
    public Vaccine convertDTOtoEntity(VaccineDTO document){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(document, Vaccine.class);
    }
}
