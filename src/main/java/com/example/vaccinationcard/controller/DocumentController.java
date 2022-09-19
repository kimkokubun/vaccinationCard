package com.example.vaccinationcard.controller;

import com.example.vaccinationcard.domain.DocumentDTO;
import com.example.vaccinationcard.domain.VaccineDTO;
import com.example.vaccinationcard.models.Document;
import com.example.vaccinationcard.models.Vaccine;
import com.example.vaccinationcard.service.DocumentService;
import com.example.vaccinationcard.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    DocumentService documentService;

    @Autowired
    VaccineService vaccineService;


    @PostMapping
    public ResponseEntity<DocumentDTO> addDocument(@RequestBody @Valid DocumentDTO documentDTO){
        return ResponseEntity.ok(this.documentService.saveDocument(documentDTO));
    }

    @GetMapping
    public ResponseEntity<List<DocumentDTO>> getDocuments () throws Exception {
        return ResponseEntity.ok(this.documentService.getDocumentByUser());
    }

    @GetMapping("/vaccines")
    public ResponseEntity<List<VaccineDTO>> getVaccine(@RequestParam String cpf){
        return ResponseEntity.ok(this.vaccineService.getVaccineByDocument(cpf));
    }
}
