package com.example.vaccinationcard.service;

import com.example.vaccinationcard.domain.VaccineDTO;
import com.example.vaccinationcard.models.Document;
import com.example.vaccinationcard.models.Vaccine;
import com.example.vaccinationcard.repository.DocumentRepository;
import com.example.vaccinationcard.repository.VaccineRepository;
import com.example.vaccinationcard.service.converter.VaccineConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VaccineService {
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private VaccineRepository vaccineRepository;
    @Autowired
    private VaccineConverter converter;

    public List<VaccineDTO> getVaccineByDocument(String cpf){
        Optional<Document> documentWithVaccines = this.documentRepository.findByCpf(cpf);
        List<Vaccine> vaccines = new ArrayList<>();
        documentWithVaccines.ifPresent(document -> vaccines.addAll(document.getVaccines()));
        return vaccines.stream().map(this.converter::convertEntityToDTO).collect(Collectors.toList());
    }

    public List<VaccineDTO> getAllVaccines(){
        return this.vaccineRepository.findAll().stream().map(this.converter::convertEntityToDTO).collect(Collectors.toList());
    }
}
