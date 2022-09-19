package com.example.vaccinationcard.service;

import com.example.vaccinationcard.domain.DocumentDTO;
import com.example.vaccinationcard.models.Document;
import com.example.vaccinationcard.models.User;
import com.example.vaccinationcard.repository.DocumentRepository;
import com.example.vaccinationcard.service.converter.DocumentConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private DocumentConverter documentConverter;


    @Transactional
    public DocumentDTO saveDocument(DocumentDTO document) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        Document documentConv = this.documentConverter.convertDTOtoEntity(document);
        documentConv.setUser(new User(user.getId()));
        Document documentEntity = this.documentRepository.save(documentConv);
        return this.documentConverter.convertEntityToDTO(documentEntity);
    }

    public List<DocumentDTO> getDocumentByUser() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Optional<List<Document>> userDB = this.documentRepository.findByUser_Id(user.getId());
        List<Document> dbList = new ArrayList<>();
        userDB.ifPresent(dbList::addAll);
//        List<Document> dbList = userDB.map(List::of).orElseThrow(Exception::new);
        return this.documentConverter.convertEntityToDTOList(dbList);
    }

}
