package com.example.vaccinationcard.controller;

import com.example.vaccinationcard.domain.DocumentDTO;
import com.example.vaccinationcard.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    DocumentService documentService;

    @PostMapping
    public ResponseEntity<DocumentDTO> addDocument(@RequestBody @Valid DocumentDTO documentDTO){
        return ResponseEntity.ok(this.documentService.saveDocument(documentDTO));
    }
}
