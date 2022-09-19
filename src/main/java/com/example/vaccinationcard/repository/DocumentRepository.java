package com.example.vaccinationcard.repository;

import com.example.vaccinationcard.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    Optional<Document> findByCpf(String cpf);

    Optional<List<Document>> findByUser_Id(Long id);

}
