package com.example.vaccinationcard.repository;

import com.example.vaccinationcard.models.User;
import com.example.vaccinationcard.models.Vaccine;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {



}
