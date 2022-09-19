package com.example.vaccinationcard.service.converter;

import com.example.vaccinationcard.domain.DocumentDTO;
import com.example.vaccinationcard.models.Document;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.print.Doc;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DocumentConverter {

    public DocumentDTO convertEntityToDTO(Document document){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(document, DocumentDTO.class);
    }
    public Document convertDTOtoEntity(DocumentDTO documentDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(documentDTO, Document.class);
    }

    public List<DocumentDTO> convertEntityToDTOList(List<Document> documentList){
        return documentList.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }
}
