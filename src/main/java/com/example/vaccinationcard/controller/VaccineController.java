package com.example.vaccinationcard.controller;

import com.example.vaccinationcard.domain.VaccineDTO;
import com.example.vaccinationcard.repository.VaccineRepository;
import com.example.vaccinationcard.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vaccines")
public class VaccineController {

    @Autowired
    private VaccineService service;

    @GetMapping
    public ResponseEntity<List<VaccineDTO>> getAllVacines(){
        return ResponseEntity.ok(service.getAllVaccines());
    }
}
