package com.example.vaccinationcard.service;

import com.example.vaccinationcard.domain.DocumentDTO;
import com.example.vaccinationcard.models.Document;
import com.example.vaccinationcard.repository.DocumentRepository;
import com.example.vaccinationcard.service.converter.DocumentConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private DocumentConverter documentConverter;


    @Transactional
    public DocumentDTO saveDocument(DocumentDTO document) {
        Document documentEntity = this.documentRepository.save(this.documentConverter.convertDTOtoEntity(document));
        return this.documentConverter.convertEntityToDTO(documentEntity);
    }

}
