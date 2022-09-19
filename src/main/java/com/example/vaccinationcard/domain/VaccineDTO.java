package com.example.vaccinationcard.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VaccineDTO {
    private String name;
    private Integer ageInDays;
    private boolean mandatory;
    private int[] boosterInDays;
    private Integer repeatBoosterInDays;
}
